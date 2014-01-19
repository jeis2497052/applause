//
// Created by Peter Friese on 13.01.14.
//
// Copyright (c) 2013 by Peter Friese. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "List.h"

@interface List (DataMapping)
- (id)initWithAttributes:(NSDictionary *)attributes;
- (NSDictionary *)attributes;
@end