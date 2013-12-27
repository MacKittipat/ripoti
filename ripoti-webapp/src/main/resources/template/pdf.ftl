<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            /* CSS crunched with Crunch - http://crunchapp.net/ */
            body{background:#ffffff;color:#808080;margin:0px;padding:0px;font-family:"Arial" !important;}
            header{background:#205081;color:#ffffff;margin-bottom:0px;-webkit-box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);-moz-box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);z-index:2;position:relative;}header .grid{padding-top:5px;height:40px;}
            header span.project-name{font-family:"Neometric-Medium",Arial;margin-left:10px;top:-9px;position:relative;font-size:24px;color:#eef6ff;background:#4272a3;padding:2px 10px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-webkit-box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);-moz-box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);box-shadow:0px 1px 2px rgba(0, 0, 0, 0.5);}
            .title-box{position:relative;background:#eeeeee;padding-bottom:20px;z-index:1;}
            .msg-box{position:relative;background:#eeeeee;padding-bottom:20px;margin-bottom:20px;z-index:1;}.msg-box select,.msg-box input{margin-left:-10px;}
            .msg-box:after{top:100%;left:50%;border:solid transparent;content:" ";height:0;width:0;position:absolute;pointer-events:none;border-color:rgba(224, 224, 224, 0);border-top-color:#eeeeee;border-width:20px;margin-left:-20px;}
            .login-bg{background:#205081 !important;margin:0px;padding:0px;width:100%;height:100%;}
            .login-box{width:340px;height:210px;padding:20px;background:#ffffff;display:table-cell;position:absolute;left:50%;top:50%;margin-top:-115px;margin-left:-170px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 0 0 5px rgba(0, 0, 0, 0.2);box-shadow:0 0 0 5px rgba(0, 0, 0, 0.2);font-size:30px;}.login-box .login-logo{position:absolute;top:-40px;}
            .login-box .error-msg{position:absolute;top:230px;background:red;color:#ffffff;font-size:16px;width:100%;padding:10px 20px;margin-left:-20px;-moz-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 0 0 5px rgba(0, 0, 0, 0.2);box-shadow:0 0 0 5px rgba(0, 0, 0, 0.2);}
            .login-box header{background:#ffffff;color:#808080;box-shadow:none;margin:0px !important;margin-top:-10px;}.login-box header span.project-name{top:0px;margin-left:0px;border:none;background:inherit;box-shadow:none;font-size:inherit;padding:0px;}
            .login-box input{margin-bottom:12px !important;}
            .login-box span{font-family:"Neometric-Medium",Arial;text-shadow:1px 1px 0px rgba(0, 0, 0, 0.3);}
            .login-box span.project-name{color:#205081;}
            .grid input{margin-top:10px;}
            input{width:100%;font-size:18px;margin-bottom:20px;font-family:Arial;background:#eef6ff;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;box-shadow:none;border:none;padding:5px;border:1px solid #ddedff;}
            input[type=text],input[type=password]{color:#205081;}
            input[type=button],input[type=submit]{background:#205081;border:0;padding:5px;color:#ffffff;text-shadow:2px 2px 0px rgba(0, 0, 0, 0.5);}
            input[type=button]:hover,input[type=submit]:hover{background:#4272a3;}
            select{width:100%;padding:5px;font-family:"Helvetica Neue",Arial;font-size:18px;background:#f8f8f8;border:1px solid #f0f0f0;-moz-border-radius:2px;-webkit-border-radius:2px;border-radius:2px;}
            .arrow-select{width:22px;height:100%;float:right;background:#e0e0e0 url('arrow-down.png') no-repeat center;display:block;position:absolute;right:10px;top:0px;-moz-border-radius:2px;-webkit-border-radius:2px;border-radius:2px;}
            .arrow-select:hover{background:#d5d5d5 url('arrow-down.png') no-repeat center;}
            select.cute-ui{padding:5px;margin:0;font-family:"Helvetica Neue",Arial;font-size:18px;-moz-border-radius:3px !important;-webkit-border-radius:3px !important;border-radius:3px !important;-webkit-box-shadow:0 3px 0 #cccccc,0 -1px #ffffff inset;-moz-box-shadow:0 3px 0 #cccccc,0 -1px #ffffff inset;box-shadow:0 3px 0 #cccccc,0 -1px #ffffff inset;background:#ffffff;border:1px solid #f8f8f8 !important;color:#555555 !important;display:inline-block;-webkit-appearance:none;-moz-appearance:none;appearance:none;cursor:pointer;margin-top:10px;}
            @media screen and (-webkit-min-device-pixel-ratio:0){select{padding-right:18px;}}label{position:relative;}
            label:after{content:url(arrow-down.png);color:#fff;right:8px;top:5px;padding:0px;position:absolute;pointer-events:none;}
            label:before{content:'';right:0px;top:-8px;width:20px;height:33px;background:#d0d0d0;position:absolute;pointer-events:none;display:block;}
            label:hover:before{background:#e0e0e0;}
            .num-round{-moz-border-radius:30px;border-radius:30px;padding:10px;text-align:center;font-size:24px;color:#555555;background:#e0e0e0 !important;width:60px;}
            .story-box{border:1px solid #eeeeee;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.story-box .story-name{background:#eef6ff;padding:10px 15px;}.story-box .story-name img.del-row{float:right;margin-top:3px;}
            .story-box .task-table{padding:10px;}
            table{width:100%;margin-top:10px;margin-bottom:10px;cell-spacing:0px;}table td:first-child,table th:first-child{width:100px;}
            table td:nth-child(3),table th:nth-child(3){width:150px;}
            table td:last-child,table th:last-child{width:60px;text-align:right;}
            table thead th{padding:7px;text-align:left;border-bottom:1px solid #eeeeee;font-weight:bold;}
            table tbody td{padding:7px;}
            table tfoot td{padding:7px;border-top:1px solid #eeeeee;}
            table tfoot td:first-child{font-weight:bold;text-align:center;}
            table tfoot td:last-child{font-weight:bold;text-align:left !important;}
            .total-box{border:1px solid #eeeeee;position:relative;float:left;width:100%;padding:10px 0;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.total-box span{font-size:20px;color:#205081;}
            .total-box span:first-child{float:left;padding:7px;}
            .total-box span:last-child{float:right;width:220px;padding:7px;font-weight:bold;}
            .convert-box{background:#eef6ff;position:relative;float:left;width:100%;padding:17px 10px;-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}.convert-box input[type=submit]{margin:0px;}
            .convert-box p{margin:0px;padding-bottom:10px;font-weight:bold;}
            .col-3{float:left;width:33.33%;}
            span.title{text-align:center;font-size:24px;color:#555555;margin-top:10px;}
            span.vip-color{color:#205081;}
            .del-row{cursor:pointer;}

            footer{height:100px;}

        </style>
    </head>
    <body>
        <section>
            <#list ripotiIssue.parentIssues as parentIssue>
                <div class="grid grid-pad">
                    <div class="col-1-1">
                        <div class="story-box">
                            <div class="story-name">
                                <span>Story: </span><span>${parentIssue.title}</span><br/>
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
                                                <td>BT-2293</td>
                                                <td><span>${childIssue.title}</span></td>
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
                <div class="col-1-3">
                </div>
                <div class="col-1-3">
                </div>
                <div class="col-1-3">
                    <div class="total-box">
                        <span>Total</span>
                        <span>${ripotiIssue.timeSpent.value}</span>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
