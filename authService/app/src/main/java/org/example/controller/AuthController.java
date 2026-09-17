package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.Model.UserInfoDto;
import org.example.Response.JwtResponseDto;
import org.example.Service.JwtService;
import org.example.Service.RefershTokenService;
import org.example.Service.UserDetailServiceImp;
import org.example.entities.RefershToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController
{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefershTokenService refreshTokenService;

    @Autowired
    private UserDetailServiceImp userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody org.example.Model.UserInfoDto userInfoDto){
        try{
            Boolean isSignUped = userDetailsService.signUpUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefershToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(JwtResponseDto.builder().accessToken(jwtToken).
                    refreshToken(refreshToken.getToken()).build(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}