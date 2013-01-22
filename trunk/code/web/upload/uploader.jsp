<%@ page language="java" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	StringBuffer uploadUrl = new StringBuffer("http://");
	uploadUrl.append(request.getHeader("Host"));
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/admin/images/imagesUpload?folderId=" + request.getParameter("folderId"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>上传图片</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<script type="text/javascript"
			src="<%=basePath%>/scripts/jquery/jquery.js"></script>
		<script type="text/javascript"
			src="<%=path%>/upload/js/swfupload/swfupload.js"></script>
		<script type="text/javascript">
			var swfu;
			window.onload = function () {
				swfu = new SWFUpload({
					upload_url: "<%=uploadUrl.toString()%>",
					
					// File Upload Settings
					file_size_limit : "10 MB",	// 1000MB
					file_types : "*.jpg;*.png;*.jpeg;*.gif;*.bmp",
					file_types_description : "All Images",
					file_upload_limit : "0",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
	
					// Button Settings
					button_image_url : '<%=path%>/images/page/select_image_01.png',
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 120,
					button_height: 30,
					button_text : '<span class="button1"><span class="buttonSmall1"> </span></span>',
					button_text_style : '.button { vertical-align: middle;font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					// Flash Settings
					flash_url : "<%=path%>/upload/js/swfupload/swfupload.swf",
					custom_settings : {
						upload_target : "divFileProgressContainer"
					}, 
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
				swfu.startUpload();
			} 
			function fileQueueError(file, errorCode, message) {
	try {
		var imageName = "<font color='red'>文件上传错误</font>";
		var errorName = "";
		if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
			errorName = "You have attempted to queue too many files.";
		}

		if (errorName !== "") {
			alert(errorName);
			return;
		}

		switch (errorCode) {
			case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE :
				imageName = "<font color='red'>文件大小为0</font>";
				break;
			case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT :
				imageName = "<font color='red'>文件大小超过限制</font>";
				break;
			case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE :
			case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE :
			default :
				alert(message);
				break;
		}
		addReadyFileInfo(file.id, file.name, imageName, file.size);

	} catch (ex) {
		this.debug(ex);
	}
}

/**
 * 当文件选择对话框关闭消失时，如果选择的文件成功加入上传队列， 那么针对每个成功加入的文件都会触发一次该事件（N个文件成功加入队列，就触发N次此事件）。
 * 
 * @param {}
 *            file id : string, // SWFUpload控制的文件的id,通过指定该id可启动此文件的上传、退出上传等
 *            index : number, // 文件在选定文件队列（包括出错、退出、排队的文件）中的索引，getFile可使用此索引 name :
 *            string, // 文件名，不包括文件的路径。 size : number, // 文件字节数 type : string, //
 *            客户端操作系统设置的文件类型 creationdate : Date, // 文件的创建时间 modificationdate :
 *            Date, // 文件的最后修改时间 filestatus : number //
 *            文件的当前状态，对应的状态代码可查看SWFUpload.FILE_STATUS }
 */
function fileQueued(file) {
	addReadyFileInfo(file.id, file.name, "成功加载到上传队列", file.size);
}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesQueued > 0) {
			document.getElementById('btnCancel').disabled = "";
			// this.startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadProgress(file, bytesLoaded) {

	try {
		var percent = Math.ceil((bytesLoaded / file.size) * 100);

		var progress = new FileProgress(file, this.customSettings.upload_target);
		progress.setProgress(percent);
		if (percent === 100) {
			progress.setStatus("");// 正在创建缩略图...
			progress.toggleCancel(false, this);
		} else {
			//progress.setStatus("正在上传...");
			progress.toggleCancel(true, this);
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.upload_target);
		addFileInfo(file.id, "文件上传完成");
	} catch (ex) {
		this.debug(ex);
	}
}

function addFileInfo(fileId, message) {
	var row = document.getElementById(fileId);
	row.cells[2].innerHTML = "<font color='green'>" + message + "</font>";
	row.cells[3].innerHTML = "&nbsp;";
}
function addReadyFileInfo(fileid, fileName, message, size) {
	var deletestr = "<a href='javascript:deleteFile(\"" + fileid
			+ "\")'>删除</a>"
	var sizeStr = parseInt(parseInt(size) / 1000) + 'k';
	// 用表格显示
	var table = $('#infoTable');
	var tr = '<tr id="' + fileid + '">'
	tr = tr + '<td>' + fileName + '</td>';
	tr = tr + '<td>' + sizeStr + '</td>';
	tr = tr + '<td>' + message + '</td>';
	tr = tr + '<td>' + deletestr + '</td>';
	tr = tr + '</tr>';
	table.append(tr);
}

function cancelUpload() {
	var infoTable = document.getElementById("infoTable");
	var rows = infoTable.rows;
	var ids = new Array();
	var row;
	if (rows == null) {
		return false;
	}
	for (var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].id;
	}
	for (var i = 0; i < ids.length; i++) {
		deleteFile(ids[i]);
	}
}

function deleteFile(fileId) {
	// 用表格显示
	var infoTable = document.getElementById("infoTable");
	var row = document.getElementById(fileId);
	infoTable.deleteRow(row.rowIndex);
	swfu.cancelUpload(fileId, false);
}

function uploadComplete(file) {
	try {
		/*
		 * I want the next upload to continue automatically so I'll call
		 * startUpload here
		 */
		if (this.getStats().files_queued > 0) {
			this.startUpload();
		} else {
			var progress = new FileProgress(file,
					this.customSettings.upload_target);
			progress.setComplete();
			progress.setStatus("");
			progress.toggleCancel(false);
			$('#' + this.customSettings.upload_target)
					.html('<font color=\'red\'>所有文件上传完毕!</b></font>');
		closeThisPage();
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	var imageName = "<font color='red'>文件上传出错!</font>";
	var progress;
	try {
		switch (errorCode) {
			case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED :
				try {
					progress = new FileProgress(file,
							this.customSettings.upload_target);
					progress.setCancelled();
					progress.setStatus("<font color='red'>取消上传!</font>");
					progress.toggleCancel(false);
				} catch (ex1) {
					this.debug(ex1);
				}
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED :
				try {
					progress = new FileProgress(file,
							this.customSettings.upload_target);
					progress.setCancelled();
					progress.setStatus("<font color='red'>停止上传!</font>");
					progress.toggleCancel(true);
				} catch (ex2) {
					this.debug(ex2);
				}
			case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED :
				imageName = "<font color='red'>文件大小超过限制!</font>";
				break;
			default :
				alert(message);
				break;
		}
		addFileInfo(file.id, imageName);
	} catch (ex3) {
		this.debug(ex3);
	}

}

function addImage(src) {
	var newImg = document.createElement("img");
	newImg.style.margin = "5px";

	document.getElementById("thumbnails").appendChild(newImg);
	if (newImg.filters) {
		try {
			newImg.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 0;
		} catch (e) {
			// If it is not set initially, the browser will throw an error. This
			// will set it if it is not set yet.
			newImg.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity='
					+ 0 + ')';
		}
	} else {
		newImg.style.opacity = 0;
	}

	newImg.onload = function() {
		fadeIn(newImg, 0);
	};
	newImg.src = src;
}

function fadeIn(element, opacity) {
	var reduceOpacityBy = 5;
	var rate = 30; // 15 fps

	if (opacity < 100) {
		opacity += reduceOpacityBy;
		if (opacity > 100) {
			opacity = 100;
		}

		if (element.filters) {
			try {
				element.filters.item("DXImageTransform.Microsoft.Alpha").opacity = opacity;
			} catch (e) {
				// If it is not set initially, the browser will throw an error.
				// This will set it if it is not set yet.
				element.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity='
						+ opacity + ')';
			}
		} else {
			element.style.opacity = opacity / 100;
		}
	}

	if (opacity < 100) {
		setTimeout(function() {
					fadeIn(element, opacity);
				}, rate);
	}
}

/*******************************************************************************
 * FileProgress Object Control object for displaying file info
 * ******************************************
 */

function FileProgress(file, targetID) {
	this.fileProgressID = "divFileProgress";
	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		this.fileProgressWrapper = document.createElement("span");
		this.fileProgressWrapper.className = "progressWrapper";
		this.fileProgressWrapper.id = this.fileProgressID;

		this.fileProgressElement = document.createElement("span");
		this.fileProgressElement.className = "progressContainer";

		var progressCancel = document.createElement("a");
		progressCancel.className = "progressCancel";
		progressCancel.href = "#";
		progressCancel.style.visibility = "hidden";
		progressCancel.appendChild(document.createTextNode(" "));

		var progressText = document.createElement("span");
		progressText.className = "progressName";
		progressText.appendChild(document.createTextNode("上传文件: " + file.name));

		var progressBar = document.createElement("span");
		progressBar.className = "progressBarInProgress";

		var progressStatus = document.createElement("span");
		progressStatus.className = "progressBarStatus";
		progressStatus.innerHTML = "&nbsp;";

		this.fileProgressElement.appendChild(progressCancel);
		this.fileProgressElement.appendChild(progressText);
		this.fileProgressElement.appendChild(progressStatus);
		this.fileProgressElement.appendChild(progressBar);

		this.fileProgressWrapper.appendChild(this.fileProgressElement);
		//document.getElementById(targetID).style.height = "75px";
		$('#' + targetID).html('');
		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
		fadeIn(this.fileProgressWrapper, 0);

	} else {
		this.fileProgressElement = this.fileProgressWrapper.firstChild;
		this.fileProgressElement.childNodes[1].firstChild.nodeValue = "上传文件: "
				+ file.name;
	}

	this.height = this.fileProgressWrapper.offsetHeight;

}
FileProgress.prototype.setProgress = function(percentage) {
	this.fileProgressElement.className = "progressContainer green";
	this.fileProgressElement.childNodes[3].className = "progressBarInProgress";
	this.fileProgressElement.childNodes[3].style.width = percentage + "%";
};
FileProgress.prototype.setComplete = function() {
	this.fileProgressElement.className = "progressContainer blue";
	this.fileProgressElement.childNodes[3].className = "progressBarComplete";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setError = function() {
	this.fileProgressElement.className = "progressContainer red";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setCancelled = function() {
	this.fileProgressElement.className = "progressContainer";
	this.fileProgressElement.childNodes[3].className = "progressBarError";
	this.fileProgressElement.childNodes[3].style.width = "";

};
FileProgress.prototype.setStatus = function(status) {
	this.fileProgressElement.childNodes[2].innerHTML = status;
};

FileProgress.prototype.toggleCancel = function(show, swfuploadInstance) {
	this.fileProgressElement.childNodes[0].style.visibility = show
			? "visible"
			: "hidden";
	if (swfuploadInstance) {
		var fileID = this.fileProgressID;
		this.fileProgressElement.childNodes[0].onclick = function() {
			swfuploadInstance.cancelUpload(fileID);
			return false;
		};
	}
};
function closeThisPage(){
	parent.imageOperAction.uploadImageOver();

}
		</script>
		<style type="text/css">
.table_a {
	border-left: 1px solid #E7CA96;
	border-bottom: 1px solid #E7CA96;
}

.table_a td,.table_a th {
	border-top: 1px solid #E7CA96;
	border-right: 1px solid #E7CA96;
	padding: 4px 5px;
}

.table_a th,.table_c th {
	background: #FBF7EE;
}

.table_a strong.dd {
	color: #DA2B28;
}

.table_a strong.ww {
	color: #005aa0;
}

td.table_wrap {
	padding: 0;
}

td.table_wrap td,.table_wrap th,.table_wrap td {
	border-top: none;
	border-right: none;
}

td {
	word-break: break-all;
	word-wrap: break-word;
}
</style>
	</head>
	<body style="background-color: #FFFFFF;">
		<div style="margin-left: 100px; width: 900px; height: 500px;">
			<form>
				<div style="height: 30px; padding-bottom: 5px;">
					<div style="display: inline; float: left; width: 250px;">
						<div id="spanButtonPlaceholder"
							style="display: inline; float: left; margin-left: 0px;"></div>
						<div id="btnUpload" onclick="startUploadFile();"
							style="display: inline; float: right; margin-left: 130px; margin-top: -30px; cursor: pointer; height: 30px; width: 120px; background-image: url('<%=path%>/images/page/select_image_02.png');"
							onMouseUp="this.className='btn3_mouseup'"
							onmousedown="this.className='btn3_mousedown'"
							onMouseOver="this.className='btn3_mouseover'"
							onmouseout="this.className='btn3_mouseout'"></div>
					</div>
					<div id="divFileProgressContainer"
						style="width: 600px; height: 40px; margin-top: 0px;; display: inline; float: right; vertical-align: middle;">

					</div>
				</div>
			</form>
			<div id="thumbnails" style="margin-top: -20px;">
				<table id="infoTable" border="0" width="100%" cellspacing="0"
					cellpadding="0" class="table_a">
					<tr class="thead-tbl-grade">
						<th width="40%">
							图片名称
						</th>
						<th width="20%">
							图片大小
						</th>
						<th width="30%">
							信息
						</th>
						<th width="10%">
							操作
						</th>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>