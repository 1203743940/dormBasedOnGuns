/**
 * 楼栋管理初始化
 */
var Building = {
    id: "BuildingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    condition: {
        name: '',
        buildingId: ''
    }
};

/**
 * 初始化表格的列
 */
Building.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '房间号', field: 'buildingId', align: 'center', valign: 'middle', width: '50px'},
        {title: '楼栋名称', field: 'simpleName', align: 'center', valign: 'middle', sortable: true},
        {title: '楼栋全称', field: 'fullName', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'description', align: 'center', valign: 'middle', sortable: true}];
};

/**
 * 检查是否选中
 */
Building.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Building.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加楼栋
 */
Building.openAddBuilding = function () {
    this.layerIndex = layer.open({
        type: 2,
        title: '添加楼栋',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/building/building_add'
    });
};

/**
 * 打开查看楼栋详情
 */
Building.openBuildingDetail = function () {
    if (this.check()) {
        this.layerIndex = layer.open({
            type: 2,
            title: '楼栋详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/building/building_update?buildingId=' + Building.seItem.buildingId
        });
    }
};

/**
 * 删除楼栋
 */
Building.delete = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/building/delete", function () {
                Feng.success("删除成功!");
                Building.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("buildingId", Building.seItem.buildingId);
            ajax.start();
        };

        Feng.confirm("是否刪除该楼栋", operation);
    }
};

/**
 * 查询楼栋列表
 */
Building.search = function () {
    var queryData = {};
    queryData['condition'] = Building.condition.name;
    queryData['buildingId'] = Building.condition.buildingId;
    Building.table.refresh({query: queryData});
};

$(function () {

    //获取楼栋树
    var ajax = new $ax(Feng.ctxPath + "/building/treeview");
    var result = ajax.start();

    $('#buildingTree').treeview({
        selectedBackColor: "#03a9f3",
        expandIcon: 'ti-plus',
        collapseIcon: 'ti-minus',
        data: result,
        onNodeSelected: function (event, data) {
            Building.condition.buildingId = data.tags;
            Building.search();
        }
    });

    Building.app = new Vue({
        el: '#buildingPage',
        data: Building.condition
    });


    var defaultColunms = Building.initColumn();
    var table = new BSTable("BuildingTable", "/building/list", defaultColunms);
    table.setPaginationType("client");
    Building.table = table.init();
});
