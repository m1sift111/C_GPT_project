package com.example.demo1122.service;

import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Admine;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-06
 */
public interface IAdmineService extends IService<Admine> {

    ResponseResult changePassword(String admineID, String newPassword);
}
