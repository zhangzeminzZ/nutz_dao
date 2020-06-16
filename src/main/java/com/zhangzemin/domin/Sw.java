package com.zhangzemin.domin;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author zhangzemin
 * @date 2020/5/27 13:42
 */
@Table("sw")
public class Sw {
    @Column
    private String zt;
    @Column
    private String jlbz;

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getJlbz() {
        return jlbz;
    }

    public void setJlbz(String jlbz) {
        this.jlbz = jlbz;
    }
}
