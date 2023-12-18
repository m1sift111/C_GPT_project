package com.example.demo1122.controller;

import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.service.IAdmineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admine")
public class AdmineController {

    @Autowired
    private IAdmineService admineService;

    @PostMapping("/changePassword")
    @ResponseBody
    public ResponseResult changePassword(@RequestParam String admineID,
                                         @RequestParam String newPassword) {
        return admineService.changePassword(admineID, newPassword);
    }
}
