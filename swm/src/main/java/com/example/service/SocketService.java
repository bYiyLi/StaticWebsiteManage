package com.example.service;

import com.example.db.api.GroupDb;
import com.example.db.api.MdDb;
import com.example.service.api.BlogSocketApi;
import org.springframework.stereotype.Service;

@Service("socketService")
public class SocketService implements BlogSocketApi {
    final
    MdDb mdDb;
    final
    GroupDb groupDb;
    SocketService(GroupDb groupDb, MdDb mdDb){
        this.groupDb = groupDb;
        this.mdDb = mdDb;
    }
    @Override
    public String getJsonGroupById(String who,int id) {
        String content=groupDb.getGroupById(id).toString();
        return "{\"who\":\""+who+"\",\"content\":"+content+"}";
    }
}
