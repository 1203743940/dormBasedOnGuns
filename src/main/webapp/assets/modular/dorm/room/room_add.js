/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RoomAddDlg = {
    editor: null,
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
RoomAddDlg.close = function () {
    parent.layer.close(window.parent.Room.layerIndex);
};

/**
 * 验证表单
 */
RoomAddDlg.validateForm = function () {

    // //接收数据
    // // RoomAddDlg.data.roomId = $("#roomId").val();
    // RoomAddDlg.data.capacity = $("#capacity").val();
    // RoomAddDlg.data.liverNum = $("#liverNum").val();
     RoomAddDlg.data.roomId = $("#simpleName").val();

    var data = RoomAddDlg.data;

    if (!data.roomId) {
        return "请输入房间号";
    }

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
 * 提交添加角色
 */
RoomAddDlg.addSubmit = function () {
    var ajax = new $ax(Feng.ctxPath + "/room/add", function (data) {
        parent.Feng.success("添加成功!");
        window.parent.Room.table.refresh();
        parent.layer.close(window.parent.Room.layerIndex);
    }, function (data) {
        parent.Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(RoomAddDlg.data);
    ajax.start();
};

/**
 * 确认按钮
 */
RoomAddDlg.ensure = function () {
    var result = RoomAddDlg.validateForm();
    if (result === true) {
        RoomAddDlg.addSubmit();
    } else {
        Feng.alert(result);
    }
};

/**
 * 取消按钮
 */
RoomAddDlg.close = function () {
    parent.layer.close(window.parent.Room.layerIndex);
};

$(function () {

    RoomAddDlg.app = new Vue({
        el: '#roomAddForm',
        data: RoomAddDlg.data,
        methods: {
            submitForm: function (e) {
                e.preventDefault();
            },
            showRoomSelectTree: function () {
                var simpleName = encodeURIComponent("parent.RoomAddDlg.app.simpleName");
                var buildingId = encodeURIComponent("parent.RoomAddDlg.app.buildingId");
                var treeUrl = encodeURIComponent(Feng.ctxPath + "/room/tree");

                layer.open({
                    type: 2,
                    title: '房间选择',
                    area: ['300px', '400px'],
                    content: Feng.ctxPath + '/system/commonTree?formName=' + simpleName + "&formId=" + buildingId + "&treeUrl=" + treeUrl
                });
            },
            ensure: function () {
                var result = RoomAddDlg.validateForm();
                if (result === true) {
                    RoomAddDlg.addSubmit();
                } else {
                    Feng.alert(result);
                }
            },
            close: function () {
                RoomAddDlg.close();
            }
        }
    });

    //注意！vue的model绑定和layui有冲突！
    //  Feng.initLaydate(RoomAddDlg.data.birthday);


});
