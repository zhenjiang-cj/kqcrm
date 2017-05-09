if (parseInt(document.documentElement.clientWidth)>parseInt(document.documentElement.scrollWidth))
{
	pagewidth=parseInt(document.documentElement.clientWidth);
}
else
{
	pagewidth=parseInt(document.documentElement.scrollWidth);
}
if (parseInt(document.documentElement.clientHeight)>parseInt(document.documentElement.scrollHeight))
{
	pageheight=parseInt(document.documentElement.clientHeight);
}
else
{
	pageheight=parseInt(document.documentElement.scrollHeight);
}