def solution(genres, plays):
    plays_for_genre = {}
    songs_for_genre = {}
    
    for i, (genre, p) in enumerate(zip(genres, plays)):
        if genre not in plays_for_genre:
            plays_for_genre[genre] = p
            songs_for_genre[genre] = [(-p, i)]
        else:
            plays_for_genre[genre] += p
            songs_for_genre[genre].append((-p, i))
            
    for genre, songs in songs_for_genre.items():
        songs_for_genre[genre] = sorted(songs)
        
    album = []
    for _, genre in sorted([(p, g) for g, p in plays_for_genre.items()], reverse=True):
        for _, idx in songs_for_genre[genre][:2]:
            album.append(idx)
            
    return album