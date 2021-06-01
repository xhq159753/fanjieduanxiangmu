package com.xie.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Caldate extends Model {
    private Integer date;//每个月的那一天
    private Integer number;
    private Integer reservations;
}
