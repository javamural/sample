package com.dg.api.vendormanagement.util;

public class SqlQueries {

	public static final String GET_ALL_VENDOR_IND_BY_SERVICE_ID = "select vend.user_id as vend_user_id, concat(vend.first_name,' ',vend.last_name) as vendor_name, vend.user_mobile_number as vendor_mobile_num, round(vendor_avg_rating,1), "
			+ "round(lat_lng_distance (st_x(users.lat_long), st_y(users.lat_long), st_x(vend.lat_long), st_y(vend.lat_long)),2) as distance_in_km, vend.vendor_ind_id, vend.about_me "
			+ "from "
			+ "(select um.user_id,ua.address_id,adr.lat_long from user_master um, user_address ua, address adr "
			+ "where um.user_id=ua.user_id and ua.address_id=adr.address_id and ua.is_primary_flag=1 and um.user_id= :userId) users, "
			+ "(select vi.user_id,ua.address_id,adr.lat_long,ums.first_name,ums.last_name, ums.user_mobile_number, vi.about_me, vi.vendor_status_id, vi.vendor_ind_id "
			+ "from vendor_individual vi, user_address ua, address adr, user_master ums, vendor_individual_roles vrls "
			+ "where vi.user_id=ua.user_id and ua.address_id=adr.address_id and ua.user_id=ums.user_id and vi.vendor_ind_id = vrls.vendor_ind_id and ua.is_primary_flag=1 "
			+ "and vrls.vendor_role_id= :vendorRoleId) vend left join "
			+ "(select vendor_org_ind_id,vendor_user_group_id,avg(overall_rating) as vendor_avg_rating "
			+ "from vendor_rating_review vrr group by vendor_org_ind_id,vendor_user_group_id) rating on "
			+ "rating.vendor_org_ind_id=vend.user_id where users.user_id<>vend.user_id "
			+ "and lat_lng_distance (st_x(users.lat_long), st_y(users.lat_long), st_x(vend.lat_long), st_y(vend.lat_long))<=100";
}
