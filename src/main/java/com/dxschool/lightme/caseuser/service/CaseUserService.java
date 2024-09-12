package com.dxschool.lightme.caseuser.service;

import com.dxschool.lightme.caseuser.domain.Address;
import com.dxschool.lightme.caseuser.domain.repository.AddressRepository;
import com.dxschool.lightme.artist.service.ArtistService;
import com.dxschool.lightme.caseuser.controller.dto.*;
import com.dxschool.lightme.artist.domain.Artist;
import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.common.util.AddressUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CaseUserService {

    private final CaseUserRepository caseUserRepository;
    private final ArtistRepository artistRepository;
    private final AddressRepository addressRepository;
    private final ArtistService artistService;

    public CaseUserResponse find(Long userId) {
        CaseUser caseUser = caseUserRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        return CaseUserResponse.from(caseUser);
    }

    @Transactional
    public void registerCaseUser(CaseUserRequest request) {
        Artist themeArtist = artistRepository.findById(request.artistId())
                .orElseThrow(NoSuchElementException::new);

        Address newAddress = AddressUtil.parseAddress(request.address());

        Address address = addressRepository.findByCityAndDistrictAndStreet(
                newAddress.getCity(),
                newAddress.getDistrict(),
                newAddress.getStreet()
        ).orElseThrow(NoSuchElementException::new);

        CaseUser caseUser = CaseUser.builder()
                .address(address)
                .nickname(request.nickname())
                .profileLink(request.profileLink())
                .themeArtist(themeArtist)
                .build();

        CaseUser registeredUser = caseUserRepository.save(caseUser);
    }

    @Transactional
    public void updateCaseUser(Long userId, CaseUserUpdateRequest request) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        if(request.artistId() != null) {
            Artist artist = artistRepository.findById(request.artistId())
                    .orElseThrow(NoSuchElementException::new);
            caseUser.setThemeArtist(artist);
        }

        if(request.nickname() != null) {
            caseUser.setNickname(request.nickname());
        }

        if(request.address() != null) {
            Address newAddress = AddressUtil.parseAddress(request.address());
            Address address = addressRepository.findByCityAndDistrictAndStreet(
                    newAddress.getCity(),
                    newAddress.getDistrict(),
                    newAddress.getStreet()
            ).orElseThrow(NoSuchElementException::new);
            caseUser.setAddress(address);
        }

        if(request.intro() != null) {
            caseUser.setIntro(request.intro());
        }
    }

    public List<CaseUserResponse> findNearby(Long userId) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        Long addressId = caseUser.getAddress().getAddressId();
        return caseUserRepository.findTop20ByAddress_AddressId(addressId).stream()
                .map(CaseUserResponse::from)
                .toList();

    }
}
