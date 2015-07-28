
<a href="application_control.html" id="menu">
	<div class="application_control">
		<b>Application Control</b>
	</div>
</a>

<a href="wallet_administration.html" id="menu">
	<div class="wallet_administration">
		<b>Wallet Administration</b>
	</div>
</a>

<a href="home_reports.html" id="menu">
	<div class="reports">
		<b>Reports</b>
	</div>
</a>

<security:authorize ifAnyGranted="ROLE_ADMIN">
<a href="admin_users.html" id="menu">
	<div class="admin_user">
		<b>Admin Users</b>
	</div>
</a>
</security:authorize>

<a href="activity_log.html" id="menu">
	<div class="activity_log">
		<b>Activity Log</b>
	</div>
</a>

<a href="vmoney_log.html" id="menu">
	<div class="activity_log">
		<b>VMoney</b>
	</div>
</a>


