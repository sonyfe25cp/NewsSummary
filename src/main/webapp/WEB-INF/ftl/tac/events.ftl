

<table class="table">
	<thead>
		<tr>
			<th>事件名称</th><th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#list eventNames as eventName>
			<tr>
				<td>${eventName}</td>
				<td><a href="/tac/events/byName?eventName=${eventName}" class="btn btn-info">查看事件内容</a></td>
			</tr>
	</tbody>
</table>