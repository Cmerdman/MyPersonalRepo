library(tidyverse)
couriers_black = read.table("CouriersofReddit/grep/black.txt")  %>% rename(Place = V1,
                                                                           Comment = V4)
couriers_black[c(2,3,5)] = NULL
couriers_discriminate = read.table("CouriersofReddit/grep/discriminate.txt")  %>% rename(Place = V1,
                                                                                         Comment = V4)
couriers_discriminate[c(2,3,5)] = NULL
couriers_racism = read.table("CouriersofReddit/grep/racism.txt") %>% rename(Place = V1,
                                                                            Comment = V4)
couriers_racism[c(2,3,5)] = NULL
couriers_racistcomment = read.table("CouriersofReddit/grep/racistcomment.txt") %>% rename(Place = V1,
                                                                                          Comment = V4)                   #these open the file and organize them into tables
couriers_racistcomment[c(2,3,5)] = NULL

post_black = read.table("PostMates/grep/black.txt") %>% rename(Place = V1,
                                                               Comment = V4)
post_black[c(2,3,5)] = NULL
post_discriminate = read.table("PostMates/grep/discriminate.txt") %>% rename(Place = V1,
                                                                             Comment = V4)
post_discriminate[c(2,3,5)] = NULL

post_racism = read.table("PostMates/grep/racism.txt") %>% rename(Place = V1,
                                                                 Comment = V4)
post_racism[c(2,3,5)] = NULL
post_racistcomment = read.table("PostMates/grep/racistcomment.txt") %>% rename(Place = V1,
                                                                               Comment = V4)
post_racistcomment[c(2,3,5)] = NULL

#cleaning door dash starts

doordashFiles = c(list.files(path = "Doordash"))
y = 1
cleaned_data_list = list()
for(i in doordashFiles){                                                                    #loops through all the files in the Doordash folder and stores them in data frames
                                                                                            #then binds all the data frames together as one with IMO the relevant info
  link = paste("Doordash/",doordashFiles[y], sep = "")
  
  con = file(link, open = "r")
  authors = c()
  text = c()
  score = c()

  while(T){
   line = readLines(con, n = 1)
   if(length(line) == 0) break
    else if(grepl("comment_author", line)) authors <- c(authors, line)
    else if(grepl("comment_body", line)) text <- c(text, line)
    else if(grepl("comment_upvotes", line)) score <- c(score, line)
  }

  cleanValues = function(values){
   x=1
    for(i in values){
     values[x] = gsub("\"","", values[x])
     values[x] = gsub("                comment_author: ","",values[x])
     values[x] = gsub("                comment_body: ","",values[x])
     values[x] = gsub("                comment_upvotes: ","",values[x])
     values[x] = gsub(",","",values[x])
      x = x+1
   }
  return(values)
  }

  authors = cleanValues(authors)
  text = cleanValues(text)
  score = cleanValues(score)
  score = as.numeric(score)

  cleaned_data = data.frame(authors,score,text)
  cleaned_data_list[[i]] = cleaned_data
  y = y + 1
}

all_cleaned_data = do.call(rbind, cleaned_data_list)

