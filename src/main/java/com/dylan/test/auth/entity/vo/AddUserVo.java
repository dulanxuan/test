package com.dylan.test.auth.entity.vo;

import com.dylan.test.auth.entity.UserAuthEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddUserVo extends UserAuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;
}
