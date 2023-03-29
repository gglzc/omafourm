# omafourm
Tech
: MySQL:5.7
: Redis:7.5

# 登入系統使用的

Redis
: 用來驗證會員帳號信箱的驗證碼，其中每個帳戶的時效是2小時，要驗證才能進行填其他的會員基本資料，如果一直沒有驗證，則刪除帳號信息

Hibernate
: 主要使用Mysql資料庫生成以及操作

JavaMail
: 使用google SMTP發uuid的驗證碼

