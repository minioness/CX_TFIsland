package com.dxschool.lightme.caseuser.controller;

import com.dxschool.lightme.caseuser.controller.dto.CaseUserDetailResponse;
import com.dxschool.lightme.caseuser.controller.dto.CaseUserRequest;
import com.dxschool.lightme.caseuser.controller.dto.CaseUserResponse;
import com.dxschool.lightme.caseuser.controller.dto.CaseUserUpdateRequest;
import com.dxschool.lightme.caseuser.service.CaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class CaseUserController {

    private final CaseUserService caseUserService;

    @GetMapping
    public ResponseEntity<CaseUserResponse> getUserInfo(@User Long userId) {
        return ResponseEntity.ok(caseUserService.find(userId));
    }

    @GetMapping("/main")
    public ResponseEntity<CaseUserDetailResponse> getUserDetailInfo(@User Long userId) {
        return ResponseEntity.ok(caseUserService.getCaseUserDetail(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CaseUserRequest request) {
        caseUserService.registerCaseUser(request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestBody CaseUserUpdateRequest request,
                                           @User Long userId) {
        caseUserService.updateCaseUser(request.artistId(), userId);
        return ResponseEntity.noContent().build();
    }
}
