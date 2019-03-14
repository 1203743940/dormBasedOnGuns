/**
 * 房间管理初始化
 */
var Room= {
    id: "RoomTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    condition: {
        roomId: "",
        capacity: "",
        remain: ""
    },
    date:{
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
        startDate: "",
        endDate: "",
        liverStatus:"",
        keyIf: "",
        comment: ""
    },
};

/**
 * 初始化表格的列
 */
Room.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '房间号', field: 'roomId',  align: 'center', valign: 'middle', sortable: true},
        {title: '房间容量', field: 'capacity', align: 'center', valign: 'middle', sortable: true},
        {title: '住户数量', field: 'liverNum', align: 'center', valign: 'middle', sortable: true},
        {title: '床位数量', field: 'bedNum', align: 'center', valign: 'middle', sortable: true},
        {title: '桌子数量', field: 'deskNum', align: 'center', valign: 'middle', sortable: true},
        {title: '椅子数量', field: 'chairNum', align: 'center', valign: 'middle', sortable: true},
        {title: '衣柜数量', field: 'chestNum', align: 'center', valign: 'middle', sortable: true},
        {title: '空调数量', field: 'airNum', align: 'center', valign: 'middle', sortable: true},
        {title: '钥匙数量', field: 'keyNum', align: 'center', valign: 'middle', sortable: true},
        {title: '水电费', field: 'utilties', align: 'center', valign: 'middle', sortable: true},
        {title: '发布者', field: 'createrName', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Room.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Room.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加房间
 */
Room.openAddRoom = function () {
    var index = layer.open({
        type: 2,
        title: '添加房间',
        area: ['800px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/room/room_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看房间详情
 */
Room.openRoomDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房间详情',
            area: ['800px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/room/room_update?roomId=' + Room.seItem.roomId
        });
        this.layerIndex = index;
    }
};
/**
 * 打开查看房间住户详情
 */
Room.openRoomLivers = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房间住户详情',
            area: ['800px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/room/livers?roomId=' + Room.seItem.roomId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除房间
 */
Room.delete = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/room/delete", function (data) {
                Feng.success("删除成功!");
                Room.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("roomId", Room.seItem.roomId);
            ajax.start();
        };

        Feng.confirm("是否删除房间 " + Room.seItem.roomId + "?", operation);
    }
};

/**
 * 查询房间列表
 */
Room.search = function () {
    var queryData = {};
    queryData['roomId'] = Room.condition.roomId;
    queryData['capacity'] = Room.condition.capacity;
    queryData['remain'] = Room.condition.remain;

    Room.table.refresh({query: queryData});
};
/**
 * 选择房间时
 */
Room.onClickDetailRoom=function(e,treeId,treeNode){
    Room.condition.roomId = treeNode.id;
    Room.search();
}

Room.openAddLiver = function(){
    if(this.check()){
        var index = layer.open({
            type: 2,
            title: '添加住户',
            area: ['800px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/liver/liver_add/'+Room.seItem.roomId
        });
        this.layerIndex = index;
    }
}

$(function () {

    Room.app = new Vue({
        el: '#roomPage',
        data: Room.condition
    });
    var ztree=new $ZTree("roomTree","/room/selectRoomTreeList");
    ztree.bindOnClick(Room.onClickDetailRoom)
    ztree.init();
    var defaultColunms = Room.initColumn();
    var table = new BSTable("RoomTable", "/room/list", defaultColunms);
    table.setPaginationType("client");
    Room.table = table.init();


    //注意！vue的model绑定和layui有冲突！
    // Feng.initLaydate(Room.data.startDate);
});
