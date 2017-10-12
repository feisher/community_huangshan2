package com.yusong.community.ui.school.mvp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by feisher on 2017/2/28.
 */

public class TelBookData {
    /**
     * children : [{"children":[{"children":[{"children":[],"iconPath":"http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/67e747d3a3b65a4921aa3d0ac834b58c871a.jpg","id":16,"roleType":1,"orgName":"三组","tel":"45454"}],"id":15,"roleType":4,"orgName":"一245组","tel":"7788"}],"iconPath":"http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/6637975bad80aa4afaaade5aee99ff065458.jpg","id":12,"roleType":2,"orgName":"一2组","tel":"9988"}]
     * iconPath : http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/b6fe5c78ac255a4ddda81e7a60ad8f04545c.jpg
     * id : 11
     * roleType : 1
     * orgName : 一组
     * tel : 98888
     */

    @SerializedName("iconPath")
    private String iconPath;
    @SerializedName("id")
    private int id;
    @SerializedName("roleType")
    private int roleType;
    @SerializedName("roleId")
    private int roleId;
    @SerializedName("orgName")
    private String orgName;
    @SerializedName("tel")
    private String tel;

    private String imAccounId;

    public String getImAccounId() {
        return imAccounId;
    }

    public void setImAccounId(String imAccounId) {
        this.imAccounId = imAccounId;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * children : [{"children":[{"children":[],"iconPath":"http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/67e747d3a3b65a4921aa3d0ac834b58c871a.jpg","id":16,"roleType":1,"orgName":"三组","tel":"45454"}],"id":15,"roleType":4,"orgName":"一245组","tel":"7788"}]
     * iconPath : http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/6637975bad80aa4afaaade5aee99ff065458.jpg
     * id : 12
     * roleType : 2
     * orgName : 一2组
     * tel : 9988
     */

    @SerializedName("children")
    private List<ChildrenBean> children;

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getroleType() {
        return roleType;
    }

    public void setroleType(int roleType) {
        this.roleType = roleType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        @SerializedName("iconPath")
        private String iconPath;
        @SerializedName("id")
        private int id;
        @SerializedName("roleType")
        private int roleType;
        @SerializedName("roleId")
        private int roleId;
        @SerializedName("orgName")
        private String orgName;
        @SerializedName("tel")
        private String tel;
        private String imAccounId;

        public String getImAccounId() {
            return imAccounId;
        }

        public void setImAccounId(String imAccounId) {
            this.imAccounId = imAccounId;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        /**
         * children : [{"children":[],"iconPath":"http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/67e747d3a3b65a4921aa3d0ac834b58c871a.jpg","id":16,"roleType":1,"orgName":"三组","tel":"45454"}]
         * id : 15
         * roleType : 4
         * orgName : 一245组
         * tel : 7788
         */

        @SerializedName("children")
        private List<ChildrenBean1> children;

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getroleType() {
            return roleType;
        }

        public void setroleType(int roleType) {
            this.roleType = roleType;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public List<ChildrenBean1> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean1> children) {
            this.children = children;
        }

        public static class ChildrenBean1 {
            @SerializedName("id")
            private int id;
            @SerializedName("roleType")
            private int roleType;
            @SerializedName("roleId")
            private int roleId;
            @SerializedName("orgName")
            private String orgName;
            @SerializedName("tel")
            private String tel;
            @SerializedName("iconPath")
            private String iconPath;

            private String imAccounId;

            public String getImAccounId() {

                return imAccounId;
            }

            public void setImAccounId(String imAccounId) {
                this.imAccounId = imAccounId;
            }

            public String getIconPath() {
                return iconPath;
            }

            public void setIconPath(String iconPath) {
                this.iconPath = iconPath;
            }

            public int getRoleType() {
                return roleType;
            }

            public void setRoleType(int roleType) {
                this.roleType = roleType;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            /**
             * children : []
             * iconPath : http://122.224.164.50:8100//static/school/school_tel_book_portrait/2017-03-14/67e747d3a3b65a4921aa3d0ac834b58c871a.jpg
             * id : 16
             * roleType : 1
             * orgName : 三组
             * tel : 45454
             */

            @SerializedName("children")
            private List<ChildrenBean11> children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getroleType() {
                return roleType;
            }

            public void setroleType(int roleType) {
                this.roleType = roleType;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public List<ChildrenBean11> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean11> children) {
                this.children = children;
            }

            public static class ChildrenBean11 {
                @SerializedName("iconPath")
                private String iconPath;
                @SerializedName("id")
                private int id;
                @SerializedName("roleType")
                private int roleType;
                @SerializedName("roleId")
                private int roleId;
                @SerializedName("orgName")
                private String orgName;
                @SerializedName("tel")
                private String tel;
                @SerializedName("children")
                private List<ChildrenBean111> children;

                private String imAccounId;

                public String getImAccounId() {
                    return imAccounId;
                }

                public void setImAccounId(String imAccounId) {
                    this.imAccounId = imAccounId;
                }

                public int getRoleId() {
                    return roleId;
                }

                public void setRoleId(int roleId) {
                    this.roleId = roleId;
                }

                public int getRoleType() {
                    return roleType;
                }

                public void setRoleType(int roleType) {
                    this.roleType = roleType;
                }

                public String getIconPath() {
                    return iconPath;
                }

                public void setIconPath(String iconPath) {
                    this.iconPath = iconPath;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getroleType() {
                    return roleType;
                }

                public void setroleType(int roleType) {
                    this.roleType = roleType;
                }

                public String getOrgName() {
                    return orgName;
                }

                public void setOrgName(String orgName) {
                    this.orgName = orgName;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public List<ChildrenBean111> getChildren() {
                    return children;
                }

                public void setChildren(List<ChildrenBean111> children) {
                    this.children = children;
                }

                public static class ChildrenBean111 {
                    @SerializedName("iconPath")
                    private String iconPath;
                    @SerializedName("id")
                    private int id;
                    @SerializedName("roleType")
                    private int roleType;
                    @SerializedName("roleId")
                    private int roleId;
                    @SerializedName("orgName")
                    private String orgName;
                    @SerializedName("tel")
                    private String tel;
                    @SerializedName("children")
                    private List<ChildrenBean111> children;

                    private String imAccounId;

                    public String getImAccounId() {
                        return imAccounId;
                    }

                    public void setImAccounId(String imAccounId) {
                        this.imAccounId = imAccounId;
                    }

                    public int getRoleType() {
                        return roleType;
                    }

                    public void setRoleType(int roleType) {
                        this.roleType = roleType;
                    }

                    public int getRoleId() {
                        return roleId;
                    }

                    public void setRoleId(int roleId) {
                        this.roleId = roleId;
                    }

                    public String getIconPath() {
                        return iconPath;
                    }

                    public void setIconPath(String iconPath) {
                        this.iconPath = iconPath;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getroleType() {
                        return roleType;
                    }

                    public void setroleType(int roleType) {
                        this.roleType = roleType;
                    }

                    public String getOrgName() {
                        return orgName;
                    }

                    public void setOrgName(String orgName) {
                        this.orgName = orgName;
                    }

                    public String getTel() {
                        return tel;
                    }

                    public void setTel(String tel) {
                        this.tel = tel;
                    }

                    public List<ChildrenBean111> getChildren() {
                        return children;
                    }

                    public void setChildren(List<ChildrenBean111> children) {
                        this.children = children;
                    }
                }
            }

        }
    }


//    /**
//     * iconPath : http://localhost://sdfsdf/x.jpg   头像
//     * orgName : string 名称
//     * tel : string 电话
//     */
//
//    private String iconPath;
//    private String orgName;
//    private String tel;
//    private List<ChildrenBean>childrenBeanList;
//
//    public List<ChildrenBean> getChildrenBeanList() {
//        return childrenBeanList;
//    }
//
//    public void setChildrenBeanList(List<ChildrenBean> childrenBeanList) {
//        this.childrenBeanList = childrenBeanList;
//    }
//
//    public String getIconPath() {
//        return iconPath;
//    }
//
//    public void setIconPath(String iconPath) {
//        this.iconPath = iconPath;
//    }
//
//    public String getOrgName() {
//        return orgName;
//    }
//
//    public void setOrgName(String orgName) {
//        this.orgName = orgName;
//    }
//
//    public String getTel() {
//        return tel;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }
}
