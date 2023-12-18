package com.example.demo1122.mapper;

import com.example.demo1122.entity.Admine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-12-06
 */
public interface AdmineMapper extends BaseMapper<Admine> {

    /**
     * 修改密码
     *
     * @param paramMap 包含 admineID 和 newPassword 的参数映射
     * @return 受影响的行数
     */
    boolean changePassword(Map<String, Object> paramMap);
}
