library(rvest)                  #for web scraping
library(dplyr)                  #for piping


reddit = "https://www.reddit.com/"
page = read_html(reddit)                  #reads html for reddit

grabText = function(class = ""){
  page %>% html_nodes(class) %>% html_text()    #function so I don't have to write that line every time
}

name = grabText("._3wqmjmv3tb_k-PROt7qFZe ._eYtD2XCVieq6emjKBH3m")

vote = grabText("._1rZYMD_4xY3gRcSS3p8ODO")
vote = vote[c(T,F)]                               #was returning two scores so I did this to eliminate every other score

timeSince = grabText("._3jOxDPIQ0KaOWpzvSQo-1s")

numComments = grabText(".FHCV02u6Cp2zYL0fhQPsO")


sub_link = page %>% html_nodes("._3ryJoIoycVkA88fy40qNJc") %>%
  html_attr("href") %>% paste("https://www.reddit.com", ., sep = "")

get_sub = function(sub_link){
  reddit_sub = read_html(sub_link)
  sub_name = reddit_sub %>% html_nodes("._33aRtz9JtW0dIrBNKFAl0y") %>%
    html_text()
  sub_name = sub_name[] %>% paste(collapse = ", ")
  return(sub_name)
}

sub = sapply(sub_link, FUN = get_sub)


post_links = page %>% html_nodes("._2INHSNB8V5eaWp4P0rY_mE") %>%
  html_attr("href") %>% paste("https://www.reddit.com", ., sep = "")          #need to grab each individual post link so I can then get the Top comment from the posts
post_links = post_links[c(F,T)]

get_comments = function(post_link){                                                   #function to get the top comment from each post
  reddit_post = read_html(post_link)                                                  #I had to do a function because post_links was returning all links and I had to make it do one at a time
  reddit_comments = reddit_post %>% html_nodes("._1qeIAgB0cPwnLhDF9XSiJM") %>%        #this might be overly complicated, it was just the first thing that worked
    html_text()
  reddit_comments = reddit_comments[1] %>% paste(collapse = "(next comment)  ")
  return(reddit_comments)
}

comments = sapply(post_links, FUN = get_comments)


redditFrame = data.frame(Title = name,                      #data frame to hold all my variables in a nice appealing manner
                         SubReddit = sub,
                         Score = vote,
                         Time_Ago  = timeSince,
                         Num_Comments = numComments,
                         Top_Comment = comments,
                         stringsAsFactors = F
)
print(redditFrame, right=F)




