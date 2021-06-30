library(rvest)
library(dplyr)

nextDoor_states = "https://nextdoor.com/find-neighborhood/"
page = read_html(nextDoor_states)

states = page %>% html_nodes(".link") %>% html_text()

stateLink = page %>% html_nodes(".link") %>% html_attr("href")

get_cities = function(stateLink){
  cityLink = read_html(stateLink)
  cityName = cityLink %>% html_nodes(".link") %>% html_text()
  return(cityName)
}

stateCities = sapply(stateLink, FUN = get_cities)

y = 0
for(i in 1:51){
  x = lengths(stateCities[i], use.names = F)
  y = y + x
}
print(y) #number of cities on nextdoor in the us, 20,898


cityLinkPage = nextDoor_states[] %>% paste(state.abb, sep = "") 
get_links = function(cityLinkPage){
  links = read_html(cityLinkPage) %>% html_nodes(".link") %>% html_attr("href")
}
cityLink = sapply(cityLinkPage, FUN = get_links)
cityLink = as.list(unlist(cityLink, use.names = F))


get_communities = function(cityLink){
  communityLink = read_html(cityLink)
  communityName = communityLink %>% html_nodes(".link") %>% html_text()
  return(communityName)
}

totalCommunities = sapply(cityLink, FUN = get_communities)   

y = 0
for (i in 1:20898){
  x = lengths(totalCommunities[i], use.names = F)       
  y = y + x
}
print(y) #total number of communities in the US, 186,356
