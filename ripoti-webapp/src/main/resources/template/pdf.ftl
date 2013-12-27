<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            /* CSS crunched with Crunch - http://crunchapp.net/ */
            body{background:#ffffff;color:#808080;margin:0px;padding:0px;font-family:"Arial" !important;}
            .story-box{border:1px solid #eeeeee;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.story-box .story-name{background:#eef6ff;padding:10px 15px;}.story-box .story-name img.del-row{float:right;margin-top:3px;}
            .story-box .task-table{padding:10px;}
            table{width:100%;cell-spacing:0px;font-family:"Arial" !important;}
            table td:first-child,table th:first-child{width:100px;}
            table td:last-child,table th:last-child{width:150px;text-align:right;}
            table thead th{padding:7px;text-align:left;border-bottom:1px solid #eeeeee;font-weight:bold;}
            table tbody td{padding:7px;}
            table tfoot td{padding:7px;border-top:1px solid #eeeeee;}
            table tfoot td:first-child{font-weight:bold;text-align:center;}
            table tfoot td:last-child{font-weight:bold;text-align:right !important;}
            .total-box{border:1px solid #eeeeee;position:relative;float:left;width:100%;padding:10px 0;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.total-box span{font-size:20px;color:#205081;}
            .total-box span:first-child{float:left;padding:7px;font-family:"Arial" !important;}
            .total-box span:last-child{float:right;width:220px;padding:7px;font-weight:bold;text-align:right;font-family:"Arial" !important;}
            .convert-box{background:#eef6ff;position:relative;float:left;width:100%;padding:17px 10px;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.convert-box input[type=submit]{margin:0px;}
            .convert-box p{margin:0px;padding-bottom:10px;font-weight:bold;}
            .col-3{float:left;width:33.33%;}
            span.title{text-align:center;font-size:24px;color:#555555;margin-top:10px;}
            span.vip-color{color:#205081;}
            .grid {margin-top: 10px; margin-bottom: 10px;}

            footer{height:100px;}

        </style>
    </head>
    <body>
        <section>
            <div class="grid grid-pad">
                <div class="col-1-1" style="text-align: center; padding-bottom: 10px;">
                    <span class="title"><strong>Time Spent Report</strong></span>
                </div>
                <div class="col-1-1" style="text-align: left; padding-bottom: 10px;">
                    <span>Board: </span><span class="vip-color"></span>
                </div>

                <div class="col-1-1" style="text-align: left;">
                    <span>Sprint: </span><span class="vip-color"></span>
                </div>
            </div>
            <#list ripotiIssue.parentIssues as parentIssue>
                <div class="grid grid-pad">
                    <div class="col-1-1">
                        <div class="story-box">
                            <div class="story-name">
                                <span>Story: </span>
                                <span>${parentIssue.key}</span>
                                <span>${parentIssue.summary}</span><br/>
                            </div>
                            <div class="task-table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Task</th>
                                            <th>Detail</th>
                                            <th>Time</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <#list parentIssue.childIssues as childIssue>
                                            <tr>
                                                <td>${childIssue.key}</td>
                                                <td><span>${childIssue.summary}</span></td>
                                                <td><span>${childIssue.timeSpent.value}</span></td>
                                            </tr>
                                        </#list>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="2">Total</td>
                                            <td colspan="2"><span>${parentIssue.timeSpent.value}</span></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <div class="grid grid-pad">
                <div class="col-1-1">
                    <div class="total-box">
                        <span>Total</span>
                        <span>${ripotiIssue.timeSpent.value}</span>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
