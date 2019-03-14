/**
 * 楼栋管理初始化
 */
var Liver = {
    id: "LiverTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    date:{
      roomId:"",
      startDate:"",
      endDate:""
    },
    condition: {
     condition:''
    }
};

/**
 * 初始化表格的列
 */
Liver.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '房间号', field: 'buildingId', align: 'center', valign: 'middle', width: '50px'},
        {title: '房间号', field: 'roomId', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'employeeName', align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'sex', align: 'center', valign: 'middle', sortable: true},
        // {title: '出生日期', field: 'birthday', align: 'center', valign: 'middle', sortable: true},
        {title: '联系电话', field: 'phone', align: 'center', valign: 'middle', sortable: true},
        {title: '身份证号', field: 'identify', align: 'center', valign: 'middle', sortable: true},
        {title: '部门/公司', field: 'company', align: 'center', valign: 'middle', sortable: true},
        {title: '学历', field: 'education', align: 'center', valign: 'middle', sortable: true},
        {title: '职位', field: 'job', align: 'center', valign: 'middle', sortable: true},
        {title: '家庭住址', field: 'address', align: 'center', valign: 'middle', sortable: true},
        {title: '住宿日期', field: 'startDate', align: 'center', valign: 'middle', sortable: true},
        {title: '退宿日期', field: 'endDate', align: 'center', valign: 'middle', sortable: true},
        {
            title: '状态', field: 'liverStatus', align: 'center', valign: 'middle', sortable: true, formatter: function (value, row, index) {
                if (value === '正常入住') {
                    return '<button type="button" class="btn btn-xs btn-success">正常居住</button>';
                } else {
                    return '<button type="button"  class="btn btn-xs btn-danger">'+value+'</button>';
                }
            }
        },
        {title: '持有钥匙', field: 'keyIf', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'comment', align: 'center', valign: 'middle', sortable: true},
        ];
};

/**
 * 检查是否选中
 */
Liver.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length === 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Liver.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加住户
 */
Liver.openAddLiver = function () {
    this.layerIndex = layer.open({
        type: 2,
        title: '添加住户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/liver/liver_add'
    });
};

/**
 * 打开查看住户详情
 */
Liver.openLiverDetail = function () {
    if (this.check()) {
        this.layerIndex = layer.open({
            type: 2,
            title: '住户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/liver/liver_update?liverId=' + Liver.seItem.liverId
        });
    }
};

/**
 * 注销住户
 */
Liver.delete = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/liver/delete", function () {
                Feng.success("注销成功!");
                Liver.table.refresh();
            }, function (data) {
                Feng.error("注销失败!" + data.responseJSON.message + "!");
            });
            ajax.set("liverId", Liver.seItem.liverId);
            ajax.start();
        };

        Feng.confirm("是否注销该住户"+Liver.seItem.employeeName, operation);
    }
};
/**
 * 通知住户
 */
Liver.notice = function () {
    if (this.check()) {

        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/liver/notice", function () {
                Feng.success("通知成功!");
                Liver.table.refresh();
            }, function (data) {
                Feng.error("通知失败!" + data.responseJSON.message + "!");
            });
            ajax.set("liverId", Liver.seItem.liverId);
            ajax.start();
        };

        Feng.confirm("向"+Liver.seItem.employeeName+"发送通知短信", operation);
    }
};

/**
 * 查询住户列表
 */
Liver.search = function () {
    var queryData = {};
    queryData['condition'] = Liver.condition.condition;
    Liver.table.refresh({query: queryData});
};

$(function () {
    Liver.app = new Vue({
        el: '#liverPage',
        data: Liver.condition
    });

    var defaultColunms = Liver.initColumn();
    var table = new BSTable(Liver.id, "/liver/list", defaultColunms);
    table.setPaginationType("client");
    Liver.table = table.init();

    //注意！vue的model绑定和layui有冲突！
    // Feng.initLaydate(Liver.data.startDate);
    // Feng.initLaydate(Liver.data.endDate);
});
