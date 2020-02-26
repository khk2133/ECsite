package com.internousdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.template.dto.LoginDTO;
import com.internousdev.template.util.DBConnector;

public class LoginDAO {
	public LoginDTO getLoginUserInfo(String loginUserId,String loginPassword) {
		//Actionから呼ばれて、引数"loginUserId","loginPasssword"を受けとる
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		LoginDTO loginDTO = new LoginDTO();
		String sql = "SELECT * FROM login_user_transaction where login_id = ? AND login_pass=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);
		//Actionから受け取った引数をselect文にセット

			ResultSet rs = ps.executeQuery();
		//実行

			if(rs.next()) {
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));
		//  DBからストリング型で取得、DTOに送る

				if(rs.getString("login_id")!=null) {
			//  DBからlogin_idの値を受け取れたら
					loginDTO.setLoginFlg(true);
			//  DTOのsetLoginFlg(boolean型)に引数trueを渡す
				}
			}
		}catch(Exception e) {
				e.printStackTrace();
			}
			return loginDTO;
		//  Actionに返す
		}

	}

