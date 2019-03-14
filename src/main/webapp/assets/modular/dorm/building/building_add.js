/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var BuildingInfoDlg = {
    data: {
        simpleName: "",
        fullName: "",
        description: "",
        sort: "",
        pid: "",
        pName: ""
    }
};

/**
 * 关闭此对话框
 */
BuildingInfoDlg.close = function () {
    parent.layer.close(window.parent.Building.layerIndex);
};

/**
 * 验证表单
 */
BuildingInfoDlg.validateForm = function () {

    var data = BuildingInfoDlg.data;

    if (!data.simpleName) {
        return "请输入楼栋名称";
    }
    if (!data.fullName) {
        return "请输入上级楼属名称";
    }
    if (!data.pid) {
        return "请选择上级楼属";
    }

    return true;
};

/**
 * 提交添加楼栋
 */
BuildingInfoDlg.addSubmit = function () {
    var ajax = new $ax(Feng.ctxPath + "/building/add", function (data) {
        parent.Feng.success("添加成功!");
        window.parent.Building.table.refresh();
        BuildingInfoDlg.close();
    }, function (data) {
        parent.Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(BuildingInfoDlg.data);
    ajax.start();
};

$(function () {

    BuildingInfoDlg.app = new Vue({
        el: '#buildingForm',
        data: BuildingInfoDlg.data,
        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            showBuildingSelectTree: function () {
                var formName = encodeURIComponent("parent.BuildingInfoDlg.app.pName");
                var formId = encodeURIComponent("parent.BuildingInfoDlg.app.pid");
                var treeUrl = encodeURIComponent(Feng.ctxPath + "/building/tree");

                layer.open({
                    type: 2,
                    title: '楼栋选择',
                    area: ['300px', '400px'],
                    content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl
                });
            },
            ensure: function () {
                var result = BuildingInfoDlg.validateForm();
                if (result === true) {
                    BuildingInfoDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                BuildingInfoDlg.close();
            }
        }
    });
});
