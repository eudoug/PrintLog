Aplicação Web Para Registro de Impressão

Requisitos para funcionamento:
	Primeira Parte:
	Ativar os logs de impressão e a opção de mostrar o nome do arquivo de impressão no log;
	Abrir o Event Viewer e localizar a opção:
	Log de Aplicações e Serviços -> Microsoft -> Windows -> PrintService -> Microsoft-Windows-PrintService/Operational;
	Clicar em Properties e marcar a opção Ativar Logs;
	Definir o tamanho do log de acordo com a sua estrutura. Deixe a opção “Substituir eventos”;
	Acessar “gpedit.msc” no servidor onde estão instaladas as impressoras e localizar a opção: 
	Configuração do Computador -> Modelos Administrativos -> Impressoras -> “Allow job name in event logs”;
	Acessar o gerenciador de impressão, em impressoras, clicar em propriedades e na aba “compartilhamento” desabilitar a opção "Processar trabalhos de impressão em computadores cliente"
	Segunda Parte:
	Acessar o Visualizador de Eventos, clicar sobre o EventID307 e clicar na opção “Anexar Tarefa à este Evento” e configurar de forma que o evento acesse powershell.exe sempre que for chamado;
	Abra o Notepad++ e a baixo da tag </Subscription> incluir as tags conforme abaixo:
	
	

	<Triggers>
	<EventTrigger>
	<Enabled>true</Enabled>
	<Subscription><QueryList><Query Id="0" Path="Microsoft-Windows-PrintService/Operational"><Select Path="Microsoft-Windows-PrintService/Operational">*[System[Provider[@Name='Microsoft-Windows-PrintService'] and EventID=307]]</Select></Query></QueryList></Subscription> 
	</EventTrigger>
	</Triggers>
	
	...
	
	<Actions Context="Author">
	<Exec>
	<Command>powershell.exe</Command>
	</Exec>
	</Actions>
	
	Arquivo Alterado:
	<Triggers>
	<EventTrigger>
	<Enabled>true</Enabled>
	<Subscription><QueryList><Query Id="0" Path="Microsoft-Windows-PrintService/Operational"><Select Path="Microsoft-Windows-PrintService/Operational">*[System[Provider[@Name='Microsoft-Windows-PrintService'] and EventID=307]]</Select></Query></QueryList></Subscription>
	<ValueQueries>
	<Value name="Address">Event/UserData/DocumentPrinted/Param6</Value>
	<Value name="Client">Event/UserData/DocumentPrinted/Param4</Value>
	<Value name="EventID">Event/System/EventID</Value>
	<Value name="FileName">Event/UserData/DocumentPrinted/Param2</Value>
	<Value name="JobBytes">Event/UserData/DocumentPrinted/Param7</Value>
	<Value name="JobID">Event/UserData/DocumentPrinted/Param1</Value>
	<Value name="PageCount">Event/UserData/DocumentPrinted/Param8</Value>
	<Value name="Printer">Event/UserData/DocumentPrinted/Param5</Value>
	<Value name="TimeCreated">Event/System/TimeCreated/@SystemTime</Value>
	<Value name="User">Event/UserData/DocumentPrinted/Param3</Value>
	</ValueQueries>
	</EventTrigger>
	</Triggers>
	
	...
	
	<Actions Context="Author">
	<Exec>
	<Command>powershell.exe</Command>
	<Arguments>-command C:\printlog\printlog.ps1 '$(EventID)' '$(TimeCreated)' '$(JobID)' '$(FileName)' '$(User)' '$(Client)' '$(Printer)' '$(Address)' '$(JobBytes)' '$(PageCount)'</Arguments>
	</Exec>
	</Actions>

>
	Na aba Geral, marcar a opção: “Executar estando o usuário conectado ou não” e também “Executar com privilégios mais altos”;
	Na aba Condições, desmarcar a opção: “Iniciar a tarefa somente se o computador estiver ligado à rede elétrica”;
	Na aba Configurações, alterar a opção: “Se a tarefa já estiver sendo executada, a seguinte regra será aplicada” para “Colocar uma nova instância na fila”;
	Terceira Parte
	A partir desse momento os logs estarão sendo capturados e enviados para o MySQL. É importante que as informações de conexão sejam anotadas para que sejam colocadas nas configurações da aplicação.
	Características da Aplicação:
	A aplicação PrintLog foi criada utilizando as seguintes tecnologias:
	CDI
	Hibernate
	JSF
	Maven

Fonte: http://www.huttel.com.br/2016/07/salvar-log-de-impressoes-do-windows-server-2012-em-banco-de-dados-mysql/
