package com.example.demo1122.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Admine;
import com.example.demo1122.mapper.AdmineMapper;
import com.example.demo1122.service.IAdmineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-06
 */

@Service
public class AdmineServiceImpl extends ServiceImpl<AdmineMapper, Admine> implements IAdmineService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult changePassword(String admineID, String newPassword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("admineID", admineID);
        paramMap.put("newPassword", passwordEncoder.encode(newPassword));
        boolean result= baseMapper.changePassword(paramMap);
        if(result){
            return new ResponseResult(126, "修改成功");
        }
        else {
            return new ResponseResult(127,"修改失败");
        }
    }
}

