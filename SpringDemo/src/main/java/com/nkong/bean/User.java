package com.nkong.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.ASSIGN_UUID)
    public int id;
    public String name;
    public int age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;
}
