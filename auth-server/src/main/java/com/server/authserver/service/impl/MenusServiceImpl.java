package com.server.authserver.service.impl;

import com.server.authserver.entity.Menus;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.authserver.dao.MenusMapper;
import com.server.authserver.service.MenusService;
@Service
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus> implements MenusService{

}
