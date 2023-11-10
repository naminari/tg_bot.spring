package com.java.bot.dto;

import lombok.extern.slf4j.Slf4j;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserMapper implements RowMapper {

    public User mapRow(ResultSet rs) throws SQLException {
        var entity = new User(
                rs.getString("tg_name"),
                rs.getLong("chat_id")
        );
        log.trace("mapRow(): entity = [{}]", entity);
        return entity;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        return new int[0];
    }
}
