package com.vti.controller;

import com.vti.dto.ChangePublicAddrAndPhoneDTO;
import com.vti.dto.ChangePublicProfileDTO;
import com.vti.dto.ProfileDTO;
import com.vti.entity.User;
import com.vti.service.implement.IUserService;
import com.vti.utils.UserDetailsUtils;
import com.vti.validation.user.EmailNotExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
        // get entity
        boolean result = userService.existsUserByEmail(email);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/userName/{userName}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "userName") String userName) {
        // get entity
        boolean result = userService.existsUserByUserName(userName);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/activeUser")
    // validate: check exists, check not expired
    public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
        // active user
        try {
            userService.activeUser(token);
        } catch (Exception e) {
            throw new RuntimeException("Tài khoản đã được kích hoạt. Vui lòng quay lại trang chủ và tiến hành đăng nhập vào hệ thống.");
        }

        return new ResponseEntity<>("Active success!", HttpStatus.OK);
    }

    // resend confirm
    @GetMapping("/userRegistrationConfirmRequest")
    // validate: email exists, email not active
    public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam @EmailNotExists String email) {

        userService.sendConfirmUserRegistrationViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
    }

    // reset password confirm
    @GetMapping("/resetPasswordRequest")
    // validate: email exists, email not active
    public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

        userService.resetPasswordViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
    }

    // resend reset password
    @GetMapping("/resendResetPassword")
    // validate: email exists, email not active
    public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

        userService.sendResetPasswordViaEmail(email);

        return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
    }

    @GetMapping("/resetPassword")
    // validate: check exists, check not expired
    public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

        // reset password
        userService.resetPassword(token, newPassword);

        return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
    }

    @GetMapping("/profile")
    // validate: check exists, check not expired
    public ResponseEntity<?> getUserProfile() {

        // get user info
        User user = userService.findUserByUserName(UserDetailsUtils.UserDetails().getUsername());

        // convert user entity to user dto
        ProfileDTO profileDto = new ProfileDTO(
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().getERole(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getStatus().toString()
        );
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    // in profile
    @PutMapping("/fullProfile")
    // validate: check exists, check not expired
    public ResponseEntity<?> changeUserProfile(@Valid @RequestBody ChangePublicProfileDTO dto) {

        userService.changeUserProfile(UserDetailsUtils.UserDetails().getUsername(), dto);

        return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
    }

    // in payment
    @PutMapping("/paymentProfile")
    // validate: check exists, check not expired
    public ResponseEntity<?> changeAddrAndPhone(@Valid @RequestBody ChangePublicAddrAndPhoneDTO dto) {

        userService.changeAddrAndPhone(UserDetailsUtils.UserDetails().getUsername(), dto);

        return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
    }

}
