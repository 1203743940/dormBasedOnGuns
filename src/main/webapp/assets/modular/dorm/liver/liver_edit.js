/**
 * 角色详情对话框（可用于添加和修改对话框）
 */

var LiverEditDlg = {
    editor: null,
    data: {
        liverId:"",
        roomId: "",
        employeeName: "",
        sex: "",
        birthday: "",
        phone: "",
        identify: "",
        company: "",
        education: "",
        job: "",
        address: "",
        startDate:"" ,
        endDate: "",
        liverStatus:"",
        keyIf: "",
        comment: ""
    }
};

/**
 * 关闭此对话框
 */
LiverEditDlg.close = function () {
    parent.layer.close(window.parent.Liver.layerIndex);
};

/**
 * 验证表单
 */
LiverEditDlg.validateForm = function () {

    // //接收数据
    LiverEditDlg.data.liverId = Feng.getUrlParam("liverId");

    var data = LiverEditDlg.data;

    if (!data.employeeName) {
        return "请输入住户姓名";
    }

    return true;
};

/**
 * 提交添加角色
 */
LiverEditDlg.addSubmit = function () {
    LiverEditDlg.data.liverId=$("#liverId").val();
    LiverEditDlg.data.startDate=$("#startDateSelect").val();
    LiverEditDlg.data.endDate=$("#endDateSelect").val();

    var ajax = new $ax(Feng.ctxPath + "/liver/update", function (data) {
        parent.Feng.success("修改成功!");
        window.parent.Liver.table.refresh();
        parent.layer.close(window.parent.Liver.layerIndex);
    }, function (data) {
        parent.Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(LiverEditDlg.data);
    ajax.start();
};

/**
 * 确认按钮
 */
LiverEditDlg.ensure = function () {
    var result = LiverEditDlg.validateForm();
    if (result === true) {
        LiverEditDlg.addSubmit();
    } else {
        Feng.alert(result);
    }
};

/**
 * 取消按钮
 */
LiverEditDlg.close = function () {
    parent.layer.close(window.parent.Liver.layerIndex);
};

$(function () {
    // LiverEditDlg.data.roomId = $("#roomId").val();
    var ajax = new $ax(Feng.ctxPath + "/liver/detail/" +  Feng.getUrlParam("liverId"));
    LiverEditDlg.data = ajax.start();

    LiverEditDlg.app = new Vue({
        el: '#liverEditForm',
        data: LiverEditDlg.data,

        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            ensure: function () {
                var result = LiverEditDlg.validateForm();
                if (result === true) {
                    LiverEditDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                LiverEditDlg.close();
            }
        }
    });

    //注意！vue的model绑定和layui有冲突！
    Feng.initStartDate(LiverEditDlg.data.startDate);
    Feng.initEndDate(LiverEditDlg.data.endDate);
});
