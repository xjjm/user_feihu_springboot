package com.fh.model.vo;

public class DataTablePageBean {

    private Long draw;
    private Long length;
    private Long start;



    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "DataTablePageBean{" +
                "draw=" + draw +
                ", length=" + length +
                ", start=" + start +
                '}';
    }
}
