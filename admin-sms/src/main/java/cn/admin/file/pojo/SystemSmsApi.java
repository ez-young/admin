package cn.admin.file.pojo;

/**
 * @author cbk
 * @date 2017/12/23
 */
public class SystemSmsApi {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_sms_api.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_sms_api.api_name
     *
     * @mbg.generated
     */
    private String apiName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_sms_api.api_key
     *
     * @mbg.generated
     */
    private String apiKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_sms_api.api_signature
     *
     * @mbg.generated
     */
    private String apiSignature;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_sms_api.id
     *
     * @return the value of system_sms_api.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_sms_api.id
     *
     * @param id the value for system_sms_api.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_sms_api.api_name
     *
     * @return the value of system_sms_api.api_name
     *
     * @mbg.generated
     */
    public String getApiName() {
        return apiName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_sms_api.api_name
     *
     * @param apiName the value for system_sms_api.api_name
     *
     * @mbg.generated
     */
    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_sms_api.api_key
     *
     * @return the value of system_sms_api.api_key
     *
     * @mbg.generated
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_sms_api.api_key
     *
     * @param apiKey the value for system_sms_api.api_key
     *
     * @mbg.generated
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_sms_api.api_signature
     *
     * @return the value of system_sms_api.api_signature
     *
     * @mbg.generated
     */
    public String getApiSignature() {
        return apiSignature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_sms_api.api_signature
     *
     * @param apiSignature the value for system_sms_api.api_signature
     *
     * @mbg.generated
     */
    public void setApiSignature(String apiSignature) {
        this.apiSignature = apiSignature == null ? null : apiSignature.trim();
    }
}