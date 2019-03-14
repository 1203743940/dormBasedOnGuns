/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RoomEditDlg = {
    data: {
        roomId: "",
        capacity: "",
        liverNum: "",
        bedNum: "",
        deskNum: "",
        chairNum: "",
        chestNum: "",
        airNum: "",
        keyNum: "",
        simpleName: "",
        buildingId: "",
        comment: ""
    }
};

/**
 * 关闭此对话框
 */
RoomEditDlg.close = function () {
    parent.layer.close(window.parent.Room.layerIndex);
};
/**
 * 验证表单
 */
RoomEditDlg.validateForm = function () {

    var data = RoomEditDlg.data;
    if (!data.capacity) {
        return "请输入房间容量";
    }
    if (!data.liverNum) {
        return "请输入住户数";
    }
    // if (!data.simpleName) {
    //     return "请选择房间号";
    // }

    return true;
};

/**
 * 提交更新房间
 */
RoomEditDlg.addSubmit = function () {
    var ajax = new $ax(Feng.ctxPath + "/room/update", function (data) {
        parent.Feng.success("修改成功!");
        window.parent.Room.table.refresh();
        parent.layer.close(window.parent.Room.layerIndex);
    }, function (data) {
        parent.Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(RoomEditDlg.data);
    ajax.start();
};

/**
 * 确认按钮
 */
RoomEditDlg.ensure = function () {
    var result = RoomEditDlg.validateForm();
    if (result === true) {
        RoomEditDlg.addSubmit();
    } else {
        Feng.alert(result);
    }
};


$(function () {

    //获取楼栋信息
    var ajax = new $ax(Feng.ctxPath + "/room/detail/" +  Feng.getUrlParam("roomId"));
    RoomEditDlg.data = ajax.start();

    RoomEditDlg.app = new Vue({
        el: '#roomEditForm',
        data: RoomEditDlg.data,
        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            ensure: function () {
                var result = RoomEditDlg.validateForm();
                if (result === true) {
                    RoomEditDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                RoomEditDlg.close();
            }
        }
    });
});
