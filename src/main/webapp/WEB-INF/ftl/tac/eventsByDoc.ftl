

<table class="table">
	<thead>
		<tr>
			<th>文件名称</th><th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#list docNames as docName>
			<tr>
				<td>${docName}</td>
				<td><a href="/tac/docs/byName?docName=${docName}" class="btn btn-info">查看文件内容</a></td>
			</tr>
	</tbody>
</table>