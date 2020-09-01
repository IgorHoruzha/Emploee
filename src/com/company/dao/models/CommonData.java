package com.company.dao.models;

import java.io.Serializable;
import java.util.Objects;
class CommonData {
    int id;

    public CommonData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
