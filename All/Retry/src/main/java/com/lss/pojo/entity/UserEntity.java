package com.lss.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user 
 * 
 *
 * @author liusongsheng
 * @since 2022-06-24 15:01:02
 */
@Data
@TableName(value = "user")
public class UserEntity implements Serializable {
            
    /**
     * 自增ID
     */

    private Long id;
                
    /**
     * 用户ID
     */

    private String userid;
        
    /**
     * 用户头像
     */

    private String userhead;
        
    /**
     * 创建时间
     */

    private LocalDateTime createtime;
        
    /**
     * 更新时间
     */

    private LocalDateTime updatetime;
        
    /**
     * 
     */

    private String username;
    
    private static final long serialVersionUID = 351675731174581070L;
    
}
