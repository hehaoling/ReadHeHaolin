package com.example.administrator.readhehaolin;

import java.util.List;

public class Results {
    private List<data> data;

    public List<data> getData() {
        return data;
    }

    public void setData(List<data> data) {
        this.data = data;
    }
    @Override
    public String toString()
    {
        return "Results[data=" + data +"]";
    }
}
