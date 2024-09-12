package com.dxschool.lightme.caseuser.controller;

import com.dxschool.lightme.caseuser.controller.dto.CaseUserRequest;
import com.dxschool.lightme.caseuser.controller.dto.CaseUserResponse;
import com.dxschool.lightme.caseuser.controller.dto.CaseUserUpdateRequest;
import com.dxschool.lightme.caseuser.service.CaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class CaseUserController {

    private final CaseUserService caseUserService;

    @GetMapping
    public ResponseEntity<CaseUserResponse> getUserInfo(@SessionAttribute(name = "userId") Long userId) {
        return ResponseEntity.ok(caseUserService.find(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CaseUserRequest request) {
        caseUserService.registerCaseUser(request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestBody CaseUserUpdateRequest request,
                                           @SessionAttribute(name = "userId") Long userId) {
        caseUserService.updateCaseUser(request.artistId(), userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/near")
    public ResponseEntity<List<CaseUserResponse>> getUsersNearBy(@SessionAttribute(name = "userId") Long userId) {
        return ResponseEntity.ok(caseUserService.findNearby(userId));
    }
}
