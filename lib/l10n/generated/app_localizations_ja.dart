//gnore: unused_import
import 'package:intl/intl.dart' asntl;
import 'app_localizations.dart';

//gnore_for_file:ype=lint

///heranslations for Japanese (`ja`).
class AppLocalizationsJaxtends AppLocalizations {
AppLocalizationsJa([String locale = 'ja']) super(locale);

@override
String get navHome => 'ホーム';

@override
String get navApps => 'アプリ';

@override
String get navSettings => '設定';

@override
String getancel => 'キャンセル';

@override
String getonfirm => '確認';

@override
String get ok => 'OK';

@override
String get apply => '適用';

@override
String get noChange => '変更しない';

@override
String get newVersionFound => '新しいバージョンが利用可能です';

@override
StringurrentVersion(String version) {
return '現在のバージョン: $version';
}

@override
String latestVersion(String version) {
return '最新のバージョン: $version';
}

@override
String get later => '後で';

@override
String get goUpdate => '更新';

@override
String get sponsorSupport => '作者をサポートする';

@override
String get sponsorAuthor => 'スポンサー';

@override
String get restartScope => 'スコープを再起動';

@override
String get systemUI => 'システム UI';

@override
String get downloadManager => 'ダウンロードマネージャー';

@override
String get xmsf => 'XMSF (Xiaomi サービスフレームワーク)';

@override
String get notificationTest => '通知のテスト';

@override
String get sendTestNotification => 'テスト通知を送信';

@override
String get notes => '説明';

@override
String get detectingModuleStatus => 'モジュールの状態を検出中...';

@override
String get moduleStatus => 'モジュールの状態';

@override
String get activated => '有効';

@override
String get notActivated => '無効';

@override
String getnableInLSPosed => 'LSPosed でこのモジュールを有効化してください';

@override
String lsposedApiVersion(int version) {
return 'LSPosed API Version: $version';
}

@override
String get updateLSPosedRequired => 'LSPosed バージョンを更新してください';

@override
String get systemNotSupported => 'システムは非対応です';

@override
String systemNotSupportedSubtitle(int version) {
return 'システムは Dynamicsland に非対応です (現在のプロトコルバージョンは $version、プロトコルバージョン 3 が必要です)';
}

@override
String restartFailed(String message) {
return '再起動に失敗しました: $message';
}

@override
String get restartRootRequired => 'このアプリに root 権限が付与されているか確認してください';

@override
String get note1 => '1. このページは Dynamicsland の対応をテストするためのものであり、実際の効果を示すものではありません。';

@override
String get note2 => '2. HyperCeiler でシステム UI と MIUI フレームワークのフォーカス通知のホワイトリストを無効化してください。';

@override
String get note3 => '3. LSPosed Manager で有効化後に関連するスコープアプリを再起動する必要があります。';

@override
String get note4 => '4. 一般的なアダプティブ表示に対応しています。適切なテンプレートを確認してみてください。';

@override
String get behaviorSection => '動作';

@override
String get defaultConfigSection => 'デフォルトのチャンネル設定';

@override
String get appearanceSection => '外観';

@override
String getonfigSection => '構成';

@override
String get aboutSection => 'アプリについて';

@override
String get keepFocusNotifTitle => 'ダウンロードの一時停止後も通知を保持する';

@override
String get keepFocusNotifSubtitle => 'ダウンロードを再開するためのフォーカス通知を表示しますが、状態の同期でズレが発生する可能性があります';

@override
String get unlockAllFocusTitle => 'フォーカス通知のホワイトリストを削除';

@override
String get unlockAllFocusSubtitle => 'システム認証がない場合でもすべてのアプリでフォーカス通知を送信可能にします';

@override
String get unlockFocusAuthTitle => 'フォーカス通知の署名検証を削除';

@override
String get unlockFocusAuthSubtitle => '署名検証のバイパスとすべてのアプリでフォーカス通知を時計/ブレスレットに送信可能な状態にします (XMSF のフックが必要です)';

@override
String getheckUpdateOnLaunchTitle => '起動時に更新を確認する';

@override
String getheckUpdateOnLaunchSubtitle => 'アプリの起動時に最新のバージョンを自動で確認します';

@override
String get showWelcomeTitle => '起動時にウェルカムメッセージを表示する';

@override
String get showWelcomeSubtitle => '起動時にsland にウェルカムメッセージを表示します';

@override
String getnteractionHapticsTitle => 'インタラクションハプティクス';

@override
String getnteractionHapticsSubtitle => 'スイッチ、スライダー、ボタンでカスタム触覚フィードバックを有効にします';

@override
String getheckUpdate => '更新を確認';

@override
String get alreadyLatest => '最新のバージョンを使用しています';

@override
String get useAppIconTitle => 'アプリアイコンを使用する';

@override
String get useAppIconSubtitle => 'ダウンロードマネージャーの通知にアプリアイコンを使用します';

@override
String get roundIconTitle => 'アイコンの角を丸める';

@override
String get roundIconSubtitle => '通知アイコンの角を丸めます';

@override
String get marqueeChannelTitle => 'Island のテキストをスクロール ';

@override
String get marqueeSpeedTitle => '速度';

@override
String marqueeSpeedLabel(int speed) {
return '$speed px/秒';
}

@override
String gethemeModeTitle => 'カラーモード';

@override
String gethemeModeSystem => 'システムに従う';

@override
String gethemeModeLight => 'ライト';

@override
String gethemeModeDark => 'ダーク';

@override
String get languageTitle => '言語';

@override
String get languageAuto => 'システムに従う';

@override
String get languageZh => '中文';

@override
String get languageEn => 'English';

@override
String get languageJa => '日本語';

@override
String get languageTr => 'Türkçe';

@override
String getxportToFile => 'ファイルにエクスポート';

@override
String getxportToFileSubtitle => '構成を JSON ファイルで保存します';

@override
String getxportToClipboard => 'クリップボードにエクスポート';

@override
String getxportToClipboardSubtitle => '構成の JSON テキストをクリップボードにコピーします';

@override
String getxportConfig => '構成をエクスポート';

@override
String getxportConfigSubtitle => 'ファイルまたはクリップボードにエクスポートするか選択してください';

@override
String getmportFromFile => 'ファイルからインポート';

@override
String getmportFromFileSubtitle => 'JSON ファイルから構成を復元します';

@override
String getmportFromClipboard => 'クリップボードからインポート';

@override
String getmportFromClipboardSubtitle => 'クリップボードの JSON テキストから構成を復元します';

@override
String getmportConfig => '構成をインポート';

@override
String getmportConfigSubtitle => 'ファイルまたはクリップボードにインポートするか選択してください';

@override
String get qqGroup => 'QQ グループ';

@override
String get restartScopeApp => '設定を適用するにはスコープアプリを再起動してください';

@override
String get groupNumberCopied => 'グループ番号をクリップボードにコピーしました';

@override
StringxportedTo(String path) {
return 'エクスポート先: $path';
}

@override
StringxportFailed(Stringrror) {
return 'エクスポートに失敗しました: $error';
}

@override
String getonfigCopied => '構成をクリップボードにコピーしました';

@override
StringmportSuccess(intount) {
return '$count 個の項目をインポートしました。アプリを再起動してください。';
}

@override
StringmportFailed(Stringrror) {
return 'インポートに失敗しました: $error';
}

@override
String get appAdaptation => 'アプリのアダプティブ表示';

@override
String selectedAppsCount(intount) {
return '$count 個のアプリを選択済み';
}

@override
String getancelSelection => '選択をキャンセル';

@override
String get deselectAll => 'すべての選択を解除';

@override
String get selectAll => 'すべて選択';

@override
String get batchChannelSettings => 'チャンネルを一括で設定';

@override
String get selectEnabledApps => '有効化するアプリを選択';

@override
String get batchEnable => '一括で有効化';

@override
String get batchDisable => '一括で無効化';

@override
String get multiSelect => '複数選択';

@override
String get showSystemApps => 'システムアプリを表示';

@override
String get refreshList => 'リストを更新';

@override
String getnableAll => 'すべて有効';

@override
String get disableAll => 'すべて無効';

@override
StringnabledAppsCount(intount) {
return 'Dynamicsland は $count 個のアプリで有効です';
}

@override
StringnabledAppsCountWithSystem(intount) {
return 'Dynamicsland は $count 個のアプリで有効です (システムアプリも含む)';
}

@override
String get searchApps => 'アプリ名またはパッケージ名で検索';

@override
String get noAppsFound => 'インストール済みのアプリが見つかりません\nアプリリストの権限が有効か確認してください';

@override
String get noMatchingApps => '一致するアプリがありません';

@override
String applyToSelectedAppsChannels(intount) {
return '選択した $count 個のアプリで有効なチャンネルに適用されます';
}

@override
String get applyingConfig => '構成を適用中です...';

@override
String progressApps(int done,ntotal) {
return '進捗: $done / $total';
}

@override
String batchApplied(intount) {
return '$count 個のアプリを適用しました';
}

@override
String getannotReadChannels => '通知チャンネルを読み込めません';

@override
String get rootRequiredMessage => '通知チャンネルの読み取りには root 権限が必要です。\nroot 権限が付与されていることを確認後に再度お試しください。';

@override
String getnableAllChannels => 'すべてのチャンネルで有効';

@override
String get noChannelsFound => '通知チャンネルがありません';

@override
String get noChannelsFoundSubtitle => 'このアプリには通知チャンネルがありません。通知の読み取りはできません。';

@override
String allChannelsActive(intount) {
return '$count 個のチャンネルですべて有効';
}

@override
String selectedChannels(int selected,ntotal) {
return '$selected / $total 個のチャンネルを選択済み';
}

@override
String allChannelsDisabled(intount) {
return 'すべての $count 個のチャンネル (無効化済み)';
}

@override
String get appDisabledBanner => 'アプリが無効化されているため、以下のチャンネル設定は無効です';

@override
StringhannelImportance(Stringmportance, Stringd) {
return '重要度: $importance·$id';
}

@override
String gethannelSettings => 'チャンネルの設定';

@override
String getmportanceNone => 'なし';

@override
String getmportanceMin => '中';

@override
String getmportanceLow => '低';

@override
String getmportanceDefault => 'デフォルト';

@override
String getmportanceHigh => '高';

@override
String getmportanceUnknown => '不明';

@override
String applyToEnabledChannels(intount) {
return '有効な $count 個のチャンネルに適用されます';
}

@override
String applyToAllChannels(intount) {
return 'すべての $count 個のチャンネルに適用されます';
}

@override
String getemplateDownloadName => 'ダウンロード';

@override
String getemplateNotificationIslandName => 'Notificationsland';

@override
String getemplateNotificationIslandLiteName => 'Notificationsland|Lite';

@override
String getemplateDownloadLiteName => 'Lite|をダウンロード';

@override
String getslandSection => 'Island';

@override
String getemplate => 'テンプレート';

@override
String get rendererLabel => 'スタイル';

@override
String get rendererImageTextWithButtons4Name => '画像 + テキスト + 下部テキストボタン';

@override
String get rendererCoverInfoName => 'カバー情報 + 自動で折りたたみ';

@override
String get rendererImageTextWithRightTextButtonName => '画像 + テキスト + 右テキストボタン';

@override
String getslandIcon => 'Island のアイコン';

@override
String getslandIconLabel => '大きなsland アイコン';

@override
String getslandIconLabelSubtitle => '有効にするとsland に大きなアイコンを表示します (小さなsland は影響を受けません)';

@override
String get focusIconLabel => 'フォーカスアイコン';

@override
String get focusNotificationLabel => 'フォーカス通知';

@override
String get preserveStatusBarSmallIconLabel => 'ステータスバーアイコン';

@override
String get restoreLockscreenTitle => 'ロック画面の通知を復元する';

@override
String get restoreLockscreenSubtitle => 'ロック画面でのフォーカス通知処理をスキップし、元のプライバシーに適切な動作を保持します';

@override
String get firstFloatLabel => '最初にフロート表示';

@override
String get updateFloatLabel => '更新時にフロート表示';

@override
String get autoDisappear => '自動で無視';

@override
String get seconds => '秒';

@override
String get onlyEnabledChannels => '有効なチャンネルにのみ適用されます';

@override
StringnabledChannelsCount(intnabled,ntotal) {
return '$enabled / $total 個のチャンネルが有効';
}

@override
String getconModeAuto => '自動';

@override
String getconModeNotifSmall => '小さな通知アイコン';

@override
String getconModeNotifLarge => '大きな通知アイコン';

@override
String getconModeAppIcon => 'アプリアイコン';

@override
String get optDefault => 'デフォルト';

@override
String get optDefaultOn => 'デフォルト (ON)';

@override
String get optDefaultOff => 'デフォルト (OFF)';

@override
String get optOn => 'ON';

@override
String get optOff => 'OFF';

@override
String getrrorInvalidFormat => '無効な構成フォーマットです';

@override
String getrrorNoStorageDir => 'ストレージのディレクトリを取得できません';

@override
String getrrorNoFileSelected => 'ファイルが選択されていません';

@override
String getrrorNoFilePath => 'ファイルパスを取得できません';

@override
String getrrorEmptyClipboard => 'クリップボードは空です';

@override
String get navBlacklist => 'フォーカスのブラックリスト';

@override
String get navBlacklistSubtitle => '特定のアプリでのフォーカス通知をブロック、フローティングまたは非表示にします';

@override
String get presetGamesTitle => '人気のゲームをクイックでフィルター';

@override
String presetGamesSuccess(intount) {
return '$count 個のインストールしたゲームをブラックリストに追加しました';
}

@override
String blacklistedAppsCount(intount) {
return '$count 個のアプリのフォーカス通知をブロックしました';
}

@override
String blacklistedAppsCountWithSystem(intount) {
return '$count 個のアプリのフォーカス通知をブロックしました (システムアプリを含む)';
}

@override
String get firstFloatLabelSubtitle => 'Island が初めて通知を受信時にフォーカス通知として展開します';

@override
String get updateFloatLabelSubtitle => 'Island の更新時にフロート通知を展開します';

@override
String get marqueeChannelTitleSubtitle => 'Island で長いメッセージをスクロールします';

@override
String get focusNotificationLabelSubtitle => '通知をフォーカス通知に置き換えます (無効で元の通知が表示されます)';

@override
String get preserveStatusBarSmallIconLabelSubtitle => 'フォーカス通知を表示時にステータスバーアイコンを強制的に保持します';

@override
String get aiConfigSection => 'AI エンハンスメント';

@override
String get aiConfigTitle => 'AI 通知の概要';

@override
String get aiConfigSubtitleEnabled => '有効 · タップで AI パラメータを構成';

@override
String get aiConfigSubtitleDisabled => '無効 · タップで構成';

@override
String get aiEnabledTitle => 'AI の概要を有効化';

@override
String get aiEnabledSubtitle => 'AI がsland の左右のテキストを生成します。タイムアウトまたはエラーの発生時はフォールバックします。';

@override
String get aiApiSection => 'API パラメータ';

@override
String get aiUrlLabel => 'API URL';

@override
String get aiUrlHint => 'https://api.openai.com/v1/chat/completions';

@override
String get aiApiKeyLabel => 'API キー';

@override
String get aiApiKeyHint => 'sk-...';

@override
String get aiModelLabel => 'モデル';

@override
String get aiModelHint => 'gpt-4o-mini';

@override
String get aiPromptLabel => 'カスタムプロンプト';

@override
String get aiPromptHint => 'デフォルトを使用する場合は空欄: 左右それぞれ 6 単語または2 文字以内の重要な情報を抽出します';

@override
String get aiPromptInUserTitle => 'ユーザーメッセージにプロンプトを表示する';

@override
String get aiPromptInUserSubtitle => '一部のモデルではシステム命令がサポートされていないため、ユーザーメッセージにプロンプ​​トを表示させることができません';

@override
String get aiTimeoutTitle => 'AI レスポンスのタイムアウト';

@override
String aiTimeoutLabel(int seconds) {
return 'AI レスポンスのタイムアウト';
}

@override
String get aiTemperatureTitle => 'サンプリング温度';

@override
String get aiTemperatureSubtitle => '回答のランダム性を制御します。0 は正確、1 はより創造的になります';

@override
String get aiMaxTokensTitle => '最大トークン';

@override
String get aiMaxTokensSubtitle => 'AI が生成するレスポンスの最大数を制限します';

@override
String get aiDefaultPromptFull => '空欄のままにするとデフォルトのプロンプトを使用します: 通知からキー情報を抽出します。左右それぞれに最大 6 単語または2 文字までです。';

@override
String get aiTestButton => 'テスト接続';

@override
String get aiTestUrlEmpty => '始めに API URL を入力してください';

@override
String get aiLastLogTitle => '最近の AI リクエストログ';

@override
String get aiLastLogSubtitle => 'テスト接続や通知によってトリガーされた AI リクエストはここに表示されます';

@override
String get aiLastLogEmpty => '表示する AI リクエストログはまだありません';

@override
String get aiLastLogSourceLabel => 'ソース';

@override
String get aiLastLogTimeLabel => '時間';

@override
String get aiLastLogStatusLabel => 'ステータス';

@override
String get aiLastLogDurationLabel => '長さ';

@override
String get aiLastLogSourceNotification => '通知のトリガー';

@override
String get aiLastLogSourceSettingsTest => 'テスト設定';

@override
String get aiLastLogRendered => 'レンダリング済み';

@override
String get aiLastLogRaw => '生';

@override
String get aiLastLogCopy => 'ログをコピー';

@override
String get aiLastLogCopied => 'AI リクエストログをコピーしました';

@override
String get aiLastLogRequest => 'リクエスト';

@override
String get aiLastLogResponse => 'レスポンス';

@override
String get aiLastLogUsage => 'トークン消費';

@override
String get aiLastLogMessages => 'メッセージ';

@override
String get aiLastLogError => 'エラー';

@override
String get aiLastLogHttpCode => 'HTTP ステータス';

@override
String get aiLastLogLeftText => '左のテキスト';

@override
String get aiLastLogRightText => '右のテキスト';

@override
String get aiLastLogAssistantContent => 'モデルレスポンスのコンテンツ';

@override
String get aiConfigSaveButton => '保存';

@override
String get aiConfigSaved => 'AI の構成を保存しました';

@override
String get aiConfigTips => 'AI は各通知のアプリパッケージ、タイトル、コンテンツを受信し、短い左側 (ソース) と右側 (コンテンツ) のテキストを返します。OpenAI 形式の API (DeepSeek、Claude など) と互換性があります。レスポンスがない場合は、デフォルトのロジックにフォールバックします。';

@override
String getemplateAiNotificationIslandName => 'AI Notificationsland';

@override
String get hideDesktopIconTitle => 'デスクトップアイコンを非表示にする';

@override
String get hideDesktopIconSubtitle => 'アプリのアイコンをランチャーから非表示にします。非表示後は、LSPosed Manager 経由で開くことができます。';
}
