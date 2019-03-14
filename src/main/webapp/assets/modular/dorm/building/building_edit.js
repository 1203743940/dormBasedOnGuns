/**
 * 楼栋详情对话框（可用于添加和修改对话框）
 */
var BuildingEditInfoDlg = {
    data: {
        buildingId: "",
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
BuildingEditInfoDlg.close = function () {
    parent.layer.close(window.parent.Building.layerIndex);
};

/**
 * 验证表单
 */
BuildingEditInfoDlg.validateForm = function () {

    var data = BuildingEditInfoDlg.data;

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
 * 提交编辑楼栋
 */
BuildingEditInfoDlg.addSubmit = function () {
    var ajax = new $ax(Feng.ctxPath + "/building/update", function (data) {
        parent.Feng.success("修改成功!");
        window.parent.Building.table.refresh();
        BuildingEditInfoDlg.close();
    }, function (data) {
        parent.Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(BuildingEditInfoDlg.data);
    ajax.start();
};

$(function () {

    //获取楼栋信息
    var ajax = new $ax(Feng.ctxPath + "/building/detail/" + Feng.getUrlParam("buildingId"));
    BuildingEditInfoDlg.data = ajax.start();

    BuildingEditInfoDlg.app = new Vue({
        el: '#buildingEditForm',
        data: BuildingEditInfoDlg.data,
        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            showBuildingSelectTree: function () {
                var formName = encodeURIComponent("parent.BuildingEditInfoDlg.app.pName");
                var formId = encodeURIComponent("parent.BuildingEditInfoDlg.app.pid");
                var treeUrl = encodeURIComponent(Feng.ctxPath + "/building/tree");

                layer.open({
                    type: 2,
                    title: '楼属选择',
                    area: ['300px', '400px'],
                    content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl
                });
            },
            ensure: function () {
                var result = BuildingEditInfoDlg.validateForm();
                if (result === true) {
                    BuildingEditInfoDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                BuildingEditInfoDlg.close();
            }
        }
    });
});