package com.gym.myboot01.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName(value = "t_user_message")
public class UserMessage {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id ;

    @TableField(value = "user_id")
    private Integer uId ;

    private String description ;

    private Date birthday ;

    private String address ;

    private Double height ;

    @TableField(value = "qq")
    private String QQ ;
}
