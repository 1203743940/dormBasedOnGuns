/**
 * 角色详情对话框（可用于添加和修改对话框）
 */

var LiverAddDlg = {
    editor: null,
    data: {
        roomId: $("#roomId").val(),
        employeeName: "",
        sex: "",
        birthday: "",
        phone: "",
        identify: "",
        company: "",
        education: "",
        job: "",
        address: "",
        startDate: "",
        endDate: "",
        liverStatus:"",
        keyIf: "",
        comment: ""
    }
};

/**
 * 关闭此对话框
 */
LiverAddDlg.close = function () {
    parent.layer.close(window.parent.Room.layerIndex);
};

/**
 * 验证表单
 */
LiverAddDlg.validateForm = function () {

    // //接收数据
    LiverAddDlg.data.liverId = $("#liverId").val();

    var data = LiverAddDlg.data;

    if (!data.employeeName) {
        return "请输入住户姓名";
    }

    return true;
};

/**
 * 提交添加角色
 */
LiverAddDlg.addSubmit = function () {
    LiverAddDlg.data.startDate=$("#startDateSelect").val();
    LiverAddDlg.data.endDate=$("#endDateSelect").val();

    var ajax = new $ax(Feng.ctxPath + "/liver/add", function (data) {
        parent.Feng.success("添加成功!");
        window.parent.Room.table.refresh();
        parent.layer.close(window.parent.Room.layerIndex);
    }, function (data) {
        parent.Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(LiverAddDlg.data);
    ajax.start();
};

/**
 * 确认按钮
 */
LiverAddDlg.ensure = function () {
    var result = LiverAddDlg.validateForm();
    if (result === true) {
        LiverAddDlg.addSubmit();
    } else {
        Feng.alert(result);
    }
};

/**
 * 取消按钮
 */
LiverAddDlg.close = function () {
    parent.layer.close(window.parent.Room.layerIndex);
};

$(function () {
    LiverAddDlg.data.roomId = $("#roomId").val();
    LiverAddDlg.app = new Vue({
        el: '#liverForm',
        data: LiverAddDlg.data,

        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            ensure: function () {
                var result = LiverAddDlg.validateForm();
                if (result === true) {
                    LiverAddDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                LiverAddDlg.close();
            }
        }
    });

    //注意！vue的model绑定和layui有冲突！
      Feng.initStartDate(LiverAddDlg.data.startDate);
      Feng.initEndDate(LiverAddDlg.data.endDate);
/*    laydate.render({
        elem: '#startDate',
        theme: '#009efb',
        max: Feng.currentDate()
    });

    laydate.render({
        elem: '#endDate',
        theme: '#009efb',
        max: Feng.currentDate()
    });*/
});
