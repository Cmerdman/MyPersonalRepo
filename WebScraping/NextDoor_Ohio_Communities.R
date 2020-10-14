library(rvest)
library(dplyr)

nextDoor_cities = "https://nextdoor.com/find-neighborhood/oh/"
page = read_html(nextDoor_cities)

numCities = page %>% html_nodes(".link") %>% html_text()

cityLink = page %>% html_nodes(".link") %>% html_attr("href")

get_communities = function(cityLink){
  communityLink = read_html(cityLink)
  communityName = communityLink %>% html_nodes(".link") %>% html_text()
  return(communityName)
}

communities = sapply(cityLink, FUN = get_communities)    #outputs as a vector
y = 0
for(i in 1:872){
  x = lengths(communities[i], use.names = F)
  y = y + x
}
print(y)