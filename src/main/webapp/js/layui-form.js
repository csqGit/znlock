layui
					.use(
							[ 'form', 'layedit', 'laydate' ],
							function() {
								var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

								//创建一个编辑器
								var editIndex = layedit
										.build('LAY_demo_editor');

								//自定义验证规则
								form.verify({
									title : function(value) {
										if (value.length < 5) {
											return '标题至少得5个字符啊';
										}
									},
									pass : [ /^[\S]{6,12}$/,
											'密码必须6到12位，且不能出现空格' ],
									content : function(value) {
										layedit.sync(editIndex);
									}
								});

							});