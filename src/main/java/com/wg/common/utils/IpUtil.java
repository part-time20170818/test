package com.wg.common.utils;

import org.codehaus.jackson.JsonNode;

/**
 * 基于 http://ip.taobao.com/service/getIpInfo.php 获得ip地址的实际位置
 * @author xingxujia
 *
 */
public class IpUtil {
    public static final String UNKNOWN_LOCATION = "未知地点";
    private static final String UNKNOWN_COUNTRY = "未分配或者内网IP";
    private static final String LOCATION_SERVICE_PROTOCOL = "http://";
    private static final String LOCATION_SERVICE_HOST = "ip.taobao.com";
    private static final String LOCATION_SERVICE_API = "/service/getIpInfo.php?ip=";

    /**
     * 获得IP地址对应的实际位置
     * @param ip
     * @return IpUtil.Location 对象
     */
    public static Location getLocation(String ip){
        Location ret = null;
        String url = LOCATION_SERVICE_PROTOCOL + LOCATION_SERVICE_HOST + LOCATION_SERVICE_API + ip;
        
        JsonNode json = WebUtil.callHttpService(url, "GET", null);
        
        if (json != null ){
            if (0 == json.get("code").asInt()){
                JsonNode data = json.get("data");
                Location location = new Location();
                
                location.setCountry(data.get("country").asText().trim());
                location.setArea(data.get("area").asText().trim());
                location.setRegion(data.get("region").asText().trim());
                location.setCity(data.get("city").asText().trim());
                location.setCounty(data.get("county").asText().trim());
                location.setIsp(data.get("isp").asText().trim());
                location.setCountryId(data.get("country_id").asText().trim());
                location.setAreaId(data.get("area_id").asText().trim());
                location.setRegionId(data.get("region_id").asText().trim());
                location.setCityId(data.get("city_id").asText().trim());
                location.setCountyId(data.get("county_id").asText().trim());
                location.setIspId(data.get("isp_id").asText().trim());
                
                if ((location.getCountry()+location.getCity()).equals("")
                        || (location.getCountry().equals(UNKNOWN_COUNTRY)) ){
                    ret = null;
                } else {
                    ret = location;
                }
            }
        }
        
        return ret;
    }

    /**
     * 获得IP地址对应的实际位置字符串
     * @param ip
     * @return 代表实际位置的字符串（形如："国家/城市"）
     */
    public static String getLocationString(String ip){
        String ret = UNKNOWN_LOCATION;
        Location location = getLocation(ip);
        
        if (location != null){
            String country = location.getCountry();
            String city = location.getCity();
            
            if ((country+city).equals("") || (country.equals(UNKNOWN_COUNTRY))){
                ret = UNKNOWN_LOCATION;
            } else {
                ret = city.equals("") ? 
                        country : 
                            (country.equals("") ? city : country + "/" + city);
            }
        }
        
        return ret;
    }

    public static class Location {
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
        private String countryId;
        private String areaId;
        private String regionId;
        private String cityId;
        private String countyId;
        private String ispId;
        
        
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getArea() {
            return area;
        }
        public void setArea(String area) {
            this.area = area;
        }
        public String getRegion() {
            return region;
        }
        public void setRegion(String region) {
            this.region = region;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getCounty() {
            return county;
        }
        public void setCounty(String county) {
            this.county = county;
        }
        public String getIsp() {
            return isp;
        }
        public void setIsp(String isp) {
            this.isp = isp;
        }
        public String getCountryId() {
            return countryId;
        }
        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }
        public String getAreaId() {
            return areaId;
        }
        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }
        public String getRegionId() {
            return regionId;
        }
        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }
        public String getCityId() {
            return cityId;
        }
        public void setCityId(String cityId) {
            this.cityId = cityId;
        }
        public String getCountyId() {
            return countyId;
        }
        public void setCountyId(String countyId) {
            this.countyId = countyId;
        }
        public String getIspId() {
            return ispId;
        }
        public void setIspId(String ispId) {
            this.ispId = ispId;
        }
    }
}
