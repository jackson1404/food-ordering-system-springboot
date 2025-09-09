<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body { font-family: Arial, sans-serif; color: #333; }
    .status { font-weight: bold; }
    .approved { color: green; }
    .rejected { color: red; }
    .btn {
      display: inline-block;
      margin-top: 12px;
      padding: 10px 18px;
      background-color: #007bff;
      color: #fff !important;
      text-decoration: none;
      border-radius: 6px;
    }
  </style>
</head>
<body>
  <h2>Restaurant Approval Notification</h2>
  <p>Hello ${ownerName},</p>

  <#if status == "APPROVED">
    <p>
      Congratulations! 🎉 Your restaurant
      <strong>${restaurantName}</strong> has been
      <span class="status approved">${status}</span> by the admin.
    </p>
    <p>
        Reason: ${message}
    </p>
    <p>
      You can now log in to your account and start managing your restaurant, menu,
      and orders.
    </p>
    <a href="${loginUrl}" class="btn">Login to Dashboard</a>
  <#elseif status == "REJECTED">
    <p>
      Unfortunately, your restaurant
      <strong>${restaurantName}</strong> has been
      <span class="status rejected">${status}</span>.
    </p>
    <p>
      Reason: ${message}
    </p>
    <p>
      If you believe this was a mistake, please update your restaurant details and resubmit your application.
    </p>
    <a href="${resubmitUrl}" class="btn">Update & Resubmit</a>
  <#else>
    <p>Status update for your restaurant <strong>${restaurantName}</strong>: ${status}</p>
    <p>${message}</p>
  </#if>

  <br/>
  <p>Thank you,<br/>Food Ordering System Team</p>
</body>
</html>
