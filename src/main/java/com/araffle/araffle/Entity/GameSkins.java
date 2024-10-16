package com.araffle.araffle.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSkins {

    private int id;                     // 唯一标识符，自增主键
    private String gameName;            // 游戏名称，最长为100个字符，不能为空
    private String description;          // 游戏描述，可以存储较长的文本
    private String gameImage;           // 游戏图片的URL，最长为255个字符，可以为空
    private Timestamp createdAt;         // 创建时间，默认为当前时间
    private Timestamp updatedAt;         // 最后更新时间，默认为当前时间，并在记录更新时自动更新

    private List<Skin> skins;
}
