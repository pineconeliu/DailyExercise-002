package com.lss.self.master;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lss.self.entity.UserEntity;

/**
 * user 表数据库访问层
 * 
 *
 * @author liusongsheng
 * @since 2022-06-24 15:01:01
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
