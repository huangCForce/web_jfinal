package com.huang.city;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.tablebind.Table;

/**
 * 城市MODEL
 * @author huangchentao
 * @date Sep 18, 2013
 */
@Table(name = "city", ID = "id")
public class City extends Model<City> {
    private static final long serialVersionUID = -7753942327443828399L;
    public final static City dao = new City();

}
