package com.internousdev.template.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.util.DBConnector;

public class BuyItemDAO {
	public BuyItemDTO getBuyItemInfo() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		BuyItemDTO buyItemDTO = new BuyItemDTO();

		String sql = "SELECT id, item_name,item_price FROM item_info_transaction";
		//DBに入っているNotebook,100,50を取得
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				buyItemDTO.setId(rs.getInt("id"));
				buyItemDTO.setItemName(rs.getString("item_name"));
				buyItemDTO.setItemPrice(rs.getString("item_price"));
			//  DBからストリング型で値を取得し、DTOにセット
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return buyItemDTO;
		//Actionに返す
	}
}
